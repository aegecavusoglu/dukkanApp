package dukkan.dukkanapp.repository;

import dukkan.dukkanapp.model.StockTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StockTransactionRepository extends JpaRepository<StockTransaction, Long> {

    // Stok kalemine göre hareketler
    List<StockTransaction> findByStockItemId(Long stockItemId);

    // Tarihe göre sorgu (örn. raporlama için)
    List<StockTransaction> findByDateBetween(LocalDate start, LocalDate end);
}
