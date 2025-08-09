package dukkan.dukkanapp.service;

import dukkan.dukkanapp.model.StockTransaction;
import dukkan.dukkanapp.repository.StockTransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StockTransactionService {
    private final StockTransactionRepository stockTransactionRepository;

    public StockTransactionService(StockTransactionRepository stockTransactionRepository) {
        this.stockTransactionRepository = stockTransactionRepository;
    }

    public List<StockTransaction> getAllTransactions() {
        return stockTransactionRepository.findAll();
    }

    public Optional<StockTransaction> getTransactionById(Long id) {
        return stockTransactionRepository.findById(id);
    }

    public StockTransaction saveTransaction(StockTransaction transaction) {
        return stockTransactionRepository.save(transaction);
    }

    public void deleteTransaction(Long id) {
        stockTransactionRepository.deleteById(id);
    }

    public List<StockTransaction> getTransactionsByStockItemId(Long stockItemId) {
        return stockTransactionRepository.findByStockItemId(stockItemId);
    }

    public List<StockTransaction> getTransactionsByDateRange(LocalDate start, LocalDate end) {
        return stockTransactionRepository.findByDateBetween(start, end);
    }
    public int getCurrentStockQuantity(Long stockItemId) {
        List<StockTransaction> transactions = stockTransactionRepository.findByStockItemId(stockItemId);
        int total = 0;
        for (StockTransaction tx : transactions) {
            if ("IN".equalsIgnoreCase(tx.getType())) {
                total += tx.getQuantity();
            } else if ("OUT".equalsIgnoreCase(tx.getType())) {
                total -= tx.getQuantity();
            }
        }
        return total;
    }
    public boolean isStockLow(Long stockItemId, int threshold) {
        return getCurrentStockQuantity(stockItemId) < threshold;
    }
    
}
