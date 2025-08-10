package dukkan.dukkanapp.service;

import dukkan.dukkanapp.model.CashTransaction;
import dukkan.dukkanapp.repository.CashTransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CashTransactionService {
    private final CashTransactionRepository cashTransactionRepository;

    public CashTransactionService(CashTransactionRepository cashTransactionRepository) {
        this.cashTransactionRepository = cashTransactionRepository;
    }

    public List<CashTransaction> getAllTransactions() {
        return cashTransactionRepository.findAll();
    }

    public Optional<CashTransaction> getTransactionById(Long id) {
        return cashTransactionRepository.findById(id);
    }

    public CashTransaction saveTransaction(CashTransaction transaction) {
        return cashTransactionRepository.save(transaction);
    }

    public void deleteTransaction(Long id) {
        cashTransactionRepository.deleteById(id);
    }

    public BigDecimal getCurrentCashBalance() {
        List<CashTransaction> transactions = cashTransactionRepository.findAll();
        BigDecimal total = BigDecimal.ZERO;
        for (CashTransaction tx : transactions) {
            if ("INCOME".equalsIgnoreCase(tx.getType())) {
                total = total.add(tx.getAmount());
            } else if ("EXPENSE".equalsIgnoreCase(tx.getType())) {
                total = total.subtract(tx.getAmount());
            }
        }
        return total;
    }

    public BigDecimal getMonthlyNetBalance(int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        List<CashTransaction> incomes = cashTransactionRepository.findAllByTypeAndDateBetween("INCOME", start, end);
        List<CashTransaction> expenses = cashTransactionRepository.findAllByTypeAndDateBetween("EXPENSE", start, end);

        BigDecimal totalIncome = incomes.stream().map(CashTransaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalExpense = expenses.stream().map(CashTransaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalIncome.subtract(totalExpense);
    }
    public List<CashTransaction> getTransactionsByInvoiced(boolean invoiced) {
        return cashTransactionRepository.findByInvoiced(invoiced);
    }
    public Optional<CashTransaction> getCashTransactionById(Long id) {
        return cashTransactionRepository.findById(id);
    }


}
