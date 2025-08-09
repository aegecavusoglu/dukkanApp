package dukkan.dukkanapp.service;

import dukkan.dukkanapp.model.CashTransaction;
import dukkan.dukkanapp.repository.CashTransactionRepository;
import dukkan.dukkanapp.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReportService {
    private final CashTransactionRepository cashTransactionRepository;
    private final ExpenseRepository expenseRepository;

    public ReportService(CashTransactionRepository cashTransactionRepository, ExpenseRepository expenseRepository) {
        this.cashTransactionRepository = cashTransactionRepository;
        this.expenseRepository = expenseRepository;
    }

    public BigDecimal getMonthlyNetBalance(int year, int month) {
        // Kasadaki gelirler
        List<CashTransaction> incomes = cashTransactionRepository
                .findAllByTypeAndDateBetween("INCOME",
                        LocalDate.of(year, month, 1),
                        LocalDate.of(year, month, LocalDate.of(year, month, 1).lengthOfMonth()));
        // Kasadaki giderler
        List<CashTransaction> expenses = cashTransactionRepository
                .findAllByTypeAndDateBetween("EXPENSE",
                        LocalDate.of(year, month, 1),
                        LocalDate.of(year, month, LocalDate.of(year, month, 1).lengthOfMonth()));

        BigDecimal totalIncome = incomes.stream()
                .map(CashTransaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalExpense = expenses.stream()
                .map(CashTransaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalIncome.subtract(totalExpense);
    }
}
