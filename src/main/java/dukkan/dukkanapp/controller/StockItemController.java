package dukkan.dukkanapp.controller;

import dukkan.dukkanapp.model.StockItem;
import dukkan.dukkanapp.service.StockItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stockitems")
public class StockItemController {
    private final StockItemService stockItemService;

    public StockItemController(StockItemService stockItemService) {
        this.stockItemService = stockItemService;
    }

    // Tüm stok kalemlerini getir
    @GetMapping
    public List<StockItem> getAllStockItems() {
        return stockItemService.getAllStockItems();
    }

    // ID ile stok kalemi getir
    @GetMapping("/{id}")
    public Optional<StockItem> getStockItemById(@PathVariable Long id) {
        return stockItemService.getStockItemById(id);
    }

    // Yeni stok kalemi ekle
    @PostMapping
    public StockItem createStockItem(@RequestBody StockItem stockItem) {
        return stockItemService.saveStockItem(stockItem);
    }

    // Stok kalemi sil
    @DeleteMapping("/{id}")
    public void deleteStockItem(@PathVariable Long id) {
        stockItemService.deleteStockItem(id);
    }
}
