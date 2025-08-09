package dukkan.dukkanapp.repository;

import dukkan.dukkanapp.model.CashTransaction;
import dukkan.dukkanapp.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CashTransactionRepository extends JpaRepository<CashTransaction, Long> {
    List<CashTransaction> findAllByTypeAndDateBetween(String type, LocalDate start, LocalDate end);
    List<CashTransaction> findByInvoiced(boolean invoiced);

}
