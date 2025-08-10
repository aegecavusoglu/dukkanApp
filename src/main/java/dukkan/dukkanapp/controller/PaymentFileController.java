package dukkan.dukkanapp.controller;

import dukkan.dukkanapp.model.CashTransaction;
import dukkan.dukkanapp.model.PaymentFile;
import dukkan.dukkanapp.service.CashTransactionService;
import dukkan.dukkanapp.service.PaymentFileService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/cashtransactions/{cashTransactionId}/files")
public class PaymentFileController {

    private final PaymentFileService paymentFileService;
    private final CashTransactionService cashTransactionService;

    public PaymentFileController(PaymentFileService paymentFileService, CashTransactionService cashTransactionService) {
        this.paymentFileService = paymentFileService;
        this.cashTransactionService = cashTransactionService;
    }

    // Çoklu dosya yükleme
    @PostMapping
    public ResponseEntity<String> uploadPaymentFiles(
            @PathVariable Long cashTransactionId,
            @RequestParam("files") MultipartFile[] files) throws IOException {

        String uploadDir = "uploads/payments/";
        CashTransaction cashTransaction = cashTransactionService.getCashTransactionById(cashTransactionId).orElseThrow();

        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            String extension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            }

            String fileName = "payment_" + cashTransactionId + "_" + System.currentTimeMillis() + "_" + originalFilename;
            String filePath = uploadDir + fileName;

            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            file.transferTo(new File(filePath));

            PaymentFile paymentFile = new PaymentFile();
            paymentFile.setCashTransaction(cashTransaction);
            paymentFile.setFilePath(filePath);
            paymentFile.setFileType(extension.toUpperCase());
            paymentFile.setDescription("Ödeme dosyası");

            paymentFileService.save(paymentFile);
        }

        return ResponseEntity.ok("Dosyalar başarıyla yüklendi.");
    }

    // Ödemeye ait tüm dosyaları listele
    @GetMapping
    public List<PaymentFile> listPaymentFiles(@PathVariable Long cashTransactionId) {
        return paymentFileService.getFilesByCashTransactionId(cashTransactionId);
    }

    // Belirli dosyayı indir
    @GetMapping("/{fileId}/download")
    public ResponseEntity<Resource> downloadPaymentFile(@PathVariable Long cashTransactionId, @PathVariable Long fileId) throws IOException {
        PaymentFile fileRecord = paymentFileService.getFileById(fileId).orElseThrow();
        File file = new File(fileRecord.getFilePath());

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        String contentType = "application/octet-stream";

        if ("PDF".equalsIgnoreCase(fileRecord.getFileType())) {
            contentType = MediaType.APPLICATION_PDF_VALUE;
        } else if ("PNG".equalsIgnoreCase(fileRecord.getFileType()) || "JPG".equalsIgnoreCase(fileRecord.getFileType()) || "JPEG".equalsIgnoreCase(fileRecord.getFileType())) {
            contentType = MediaType.IMAGE_JPEG_VALUE;
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
}