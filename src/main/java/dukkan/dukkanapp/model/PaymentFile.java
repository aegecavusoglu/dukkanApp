package dukkan.dukkanapp.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class PaymentFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filePath;
    private String fileType;
    private String description;
    private LocalDateTime uploadedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "cash_transaction_id")
    private CashTransaction cashTransaction;

    public PaymentFile() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }

    public CashTransaction getCashTransaction() { return cashTransaction; }
    public void setCashTransaction(CashTransaction cashTransaction) { this.cashTransaction = cashTransaction; }
}
