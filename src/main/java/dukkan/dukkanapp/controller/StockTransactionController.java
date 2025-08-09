package dukkan.dukkanapp.controller;

import dukkan.dukkanapp.model.StockTransaction;
import dukkan.dukkanapp.service.StockTransactionService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stocktransactions")
public class StockTransactionController {
    private final StockTransactionService stockTransactionService;

    public StockTransactionController(StockTransactionService stockTransactionService) {
        this.stockTransactionService = stockTransactionService;
    }

    @GetMapping
    public List<StockTransaction> getAllTransactions() {
        return stockTransactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public Optional<StockTransaction> getTransactionById(@PathVariable Long id) {
        return stockTransactionService.getTransactionById(id);
    }

    @PostMapping
    public StockTransaction createTransaction(@RequestBody StockTransaction transaction) {
        return stockTransactionService.saveTransaction(transaction);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        stockTransactionService.deleteTransaction(id);
    }

    @GetMapping("/stockitem/{stockItemId}")
    public List<StockTransaction> getTransactionsByStockItem(@PathVariable Long stockItemId) {
        return stockTransactionService.getTransactionsByStockItemId(stockItemId);
    }

    @GetMapping("/date-range")
    public List<StockTransaction> getTransactionsByDateRange(
            @RequestParam("start") LocalDate start,
            @RequestParam("end") LocalDate end) {
        return stockTransactionService.getTransactionsByDateRange(start, end);
    }
    @GetMapping("/stockitem/{stockItemId}/current-quantity")
    public int getCurrentStockQuantity(@PathVariable Long stockItemId) {
        return stockTransactionService.getCurrentStockQuantity(stockItemId);
    }
    @GetMapping("/stockitem/{stockItemId}/is-low")
    public boolean isStockLow(@PathVariable Long stockItemId, @RequestParam(defaultValue = "50") int threshold) {
        return stockTransactionService.isStockLow(stockItemId, threshold);
    }
}
