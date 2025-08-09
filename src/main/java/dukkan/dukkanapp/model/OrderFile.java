package dukkan.dukkanapp.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class OrderFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filePath;                // Dosya yolu
    private String fileType;                // "PDF", "IMG", vs.
    private String description;             // Açıklama (opsiyonel)
    private LocalDateTime uploadedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderFile() {}

    public OrderFile(Long id, String filePath, String fileType, String description, LocalDateTime uploadedAt, Order order) {
        this.id = id;
        this.filePath = filePath;
        this.fileType = fileType;
        this.description = description;
        this.uploadedAt = uploadedAt;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
