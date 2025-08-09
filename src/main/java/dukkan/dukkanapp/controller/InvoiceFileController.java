package dukkan.dukkanapp.controller;

import dukkan.dukkanapp.model.Invoice;
import dukkan.dukkanapp.model.InvoiceFile;
import dukkan.dukkanapp.service.InvoiceFileService;
import dukkan.dukkanapp.service.InvoiceService;
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
@RequestMapping("/api/invoices/{invoiceId}/files")
public class InvoiceFileController {
    private final InvoiceFileService invoiceFileService;
    private final InvoiceService invoiceService;

    public InvoiceFileController(InvoiceFileService invoiceFileService, InvoiceService invoiceService) {
        this.invoiceFileService = invoiceFileService;
        this.invoiceService = invoiceService;
    }

    // Çoklu dosya yükleme
    @PostMapping
    public ResponseEntity<?> uploadInvoiceFiles(
            @PathVariable Long invoiceId,
            @RequestParam("files") MultipartFile[] files) throws IOException {

        String uploadDir = "uploads/invoices/";
        Invoice invoice = invoiceService.getInvoiceById(invoiceId).orElseThrow();

        for (MultipartFile file : files) {
            String filename = file.getOriginalFilename();
            String extension = "";
            if (filename != null && filename.contains(".")) {
                extension = filename.substring(filename.lastIndexOf(".") + 1);
            }
            String fileName = "invoice_" + invoiceId + "_" + System.currentTimeMillis() + "_" + filename;
            String filePath = uploadDir + fileName;

            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();
            file.transferTo(new File(filePath));

            InvoiceFile invoiceFile = new InvoiceFile();
            invoiceFile.setInvoice(invoice);
            invoiceFile.setFilePath(filePath);
            invoiceFile.setFileType(extension.toUpperCase());
            invoiceFile.setDescription("Fatura dosyası");

            invoiceFileService.save(invoiceFile);
        }

        return ResponseEntity.ok("Dosyalar yüklendi.");
    }

    // Faturaya ait tüm dosyaları listele
    @GetMapping
    public List<InvoiceFile> listInvoiceFiles(@PathVariable Long invoiceId) {
        return invoiceFileService.getFilesByInvoiceId(invoiceId);
    }

    // Belirli dosyayı indir/görüntüle
    @GetMapping("/{fileId}/download")
    public ResponseEntity<Resource> downloadInvoiceFile(@PathVariable Long invoiceId, @PathVariable Long fileId) throws IOException {
        InvoiceFile fileRecord = invoiceFileService.getFileById(fileId).orElseThrow();
        File file = new File(fileRecord.getFilePath());
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        String contentType = "application/octet-stream";
        if ("PDF".equalsIgnoreCase(fileRecord.getFileType())) contentType = MediaType.APPLICATION_PDF_VALUE;
        else if ("PNG".equalsIgnoreCase(fileRecord.getFileType()) || "JPG".equalsIgnoreCase(fileRecord.getFileType()))
            contentType = MediaType.IMAGE_JPEG_VALUE;
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
}
