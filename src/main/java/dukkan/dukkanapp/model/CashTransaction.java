package dukkan.dukkanapp.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class CashTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String type;             // "INCOME" veya "EXPENSE"
    private BigDecimal amount;
    private String description;
    private String paymentMethod;
    private Boolean invoiced;
    private String invoiceNumber;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company; // Para giren/çıkan şirket/kişi

    public CashTransaction() {}

    public CashTransaction(Long id, LocalDate date, String type, BigDecimal amount, String description,
                           String paymentMethod, Boolean invoiced, String invoiceNumber, Company company) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.paymentMethod = paymentMethod;
        this.invoiced = invoiced;
        this.invoiceNumber = invoiceNumber;
        this.company = company;
    }

    // Getter ve Setter'lar
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public Boolean getInvoiced() { return invoiced; }
    public void setInvoiced(Boolean invoiced) { this.invoiced = invoiced; }

    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }

    public Company getCompany() { return company; }
    public void setCompany(Company company) { this.company = company; }
}
