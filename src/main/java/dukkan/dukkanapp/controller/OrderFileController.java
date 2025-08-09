package dukkan.dukkanapp.controller;

import dukkan.dukkanapp.model.Order;
import dukkan.dukkanapp.model.OrderFile;
import dukkan.dukkanapp.service.OrderFileService;
import dukkan.dukkanapp.service.OrderService;
import org.apache.commons.io.FilenameUtils;
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
@RequestMapping("/api/orders/{orderId}/files")
public class OrderFileController {
    private final OrderFileService orderFileService;
    private final OrderService orderService;

    public OrderFileController(OrderFileService orderFileService, OrderService orderService) {
        this.orderFileService = orderFileService;
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<?> uploadOrderFiles(
            @PathVariable Long orderId,
            @RequestParam("files") MultipartFile[] files) throws IOException {

        String uploadDir = "uploads/orders/";
        Order order = orderService.getOrderById(orderId).orElseThrow();

        for (MultipartFile file : files) {
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            String fileName = "order_" + orderId + "_" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String filePath = uploadDir + fileName;

            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();
            file.transferTo(new File(filePath));

            OrderFile orderFile = new OrderFile();
            orderFile.setOrder(order);
            orderFile.setFilePath(filePath);
            orderFile.setFileType(extension != null ? extension.toUpperCase() : "");
            orderFile.setDescription("Sipariş dosyası");

            orderFileService.save(orderFile);
        }

        return ResponseEntity.ok("Dosyalar yüklendi.");
    }

    // Siparişe ait tüm dosyaları listele
    @GetMapping
    public List<OrderFile> listOrderFiles(@PathVariable Long orderId) {
        return orderFileService.getFilesByOrderId(orderId);
    }

    // Belirli dosyayı indir/görüntüle
    @GetMapping("/{fileId}/download")
    public ResponseEntity<Resource> downloadOrderFile(@PathVariable Long orderId, @PathVariable Long fileId) throws IOException {
        OrderFile fileRecord = orderFileService.getFileById(fileId).orElseThrow();
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
