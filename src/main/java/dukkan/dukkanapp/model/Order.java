package dukkan.dukkanapp.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;         // Sipariş açıklaması
    private BigDecimal salePrice;       // Satış fiyatı
    private BigDecimal cost;            // Maliyet (opsiyonel)
    private String status;              // Sipariş durumu
    private LocalDate deliveryDate;     // Teslim tarihi
    private Boolean isPaid;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;          // Siparişin sahibi müşteri

    @ManyToOne
    @JoinColumn(name = "paper_type_id")
    private PaperType paperType;        // Kullanılan kağıt türü

    public Order() {
    }

    public Order(Long id, String description, BigDecimal salePrice, BigDecimal cost, String status,
                 LocalDate deliveryDate, Boolean isPaid, Customer customer, PaperType paperType) {
        this.id = id;
        this.description = description;
        this.salePrice = salePrice;
        this.cost = cost;
        this.status = status;
        this.deliveryDate = deliveryDate;
        this.isPaid = isPaid;
        this.customer = customer;
        this.paperType = paperType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public PaperType getPaperType() {
        return paperType;
    }

    public void setPaperType(PaperType paperType) {
        this.paperType = paperType;
    }

}
