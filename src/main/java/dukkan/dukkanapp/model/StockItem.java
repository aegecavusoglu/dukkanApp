package dukkan.dukkanapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class StockItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;           // Malzeme adı (örn: "A4 Kuşe 200gr")
    private int quantity;          // Stokta bulunan adet
    private BigDecimal unitCost;   // Birim alış maliyeti
    private String category;       // "Kağıt", "Toner" gibi kategoriler

    public StockItem() {
    }

    public StockItem(Long id, String name, int quantity, BigDecimal unitCost, String category) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
