package dukkan.dukkanapp.controller;

import dukkan.dukkanapp.model.CashTransaction;
import dukkan.dukkanapp.service.CashTransactionService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cashtransactions")
public class CashTransactionController {
    private final CashTransactionService cashTransactionService;

    public CashTransactionController(CashTransactionService cashTransactionService) {
        this.cashTransactionService = cashTransactionService;
    }

    @GetMapping
    public List<CashTransaction> getAllTransactions() {
        return cashTransactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public Optional<CashTransaction> getTransactionById(@PathVariable Long id) {
        return cashTransactionService.getTransactionById(id);
    }

    @PostMapping
    public CashTransaction createTransaction(@RequestBody CashTransaction transaction) {
        return cashTransactionService.saveTransaction(transaction);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        cashTransactionService.deleteTransaction(id);
    }

    @GetMapping("/current-balance")
    public BigDecimal getCurrentCashBalance() {
        return cashTransactionService.getCurrentCashBalance();
    }
    @GetMapping("/monthly-net-balance")
    public BigDecimal getMonthlyNetBalance(@RequestParam int year, @RequestParam int month) {
        return cashTransactionService.getMonthlyNetBalance(year, month);
    }
    @GetMapping("/by-invoiced")
    public List<CashTransaction> getTransactionsByInvoiced(@RequestParam boolean invoiced) {
        return cashTransactionService.getTransactionsByInvoiced(invoiced);
    }

}
