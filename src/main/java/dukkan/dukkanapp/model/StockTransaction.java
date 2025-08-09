package dukkan.dukkanapp.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class StockTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stock_item_id")
    private StockItem stockItem;      // Hangi malzeme

    private int quantity;             // Alınan/kullanılan miktar
    private BigDecimal unitCost;      // Alış fiyatı (girişte doldurulur)
    private LocalDate date;           // Hareket tarihi
    private String type;              // "IN" (giriş/alım) veya "OUT" (kullanım/çıkış)
    private String description;       // Açıklama (opsiyonel)

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;          // Alım/kullanım yapılan firma (opsiyonel)

    public StockTransaction() {}

    public StockTransaction(Long id, StockItem stockItem, int quantity, BigDecimal unitCost,
                            LocalDate date, String type, String description, Company company) {
        this.id = id;
        this.stockItem = stockItem;
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.date = date;
        this.type = type;
        this.description = description;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StockItem getStockItem() {
        return stockItem;
    }

    public void setStockItem(StockItem stockItem) {
        this.stockItem = stockItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
