package dukkan.dukkanapp.controller;

import dukkan.dukkanapp.service.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // Örnek: /api/reports/monthly-net-balance?year=2024&month=6
    @GetMapping("/monthly-net-balance")
    public BigDecimal getMonthlyNetBalance(@RequestParam int year, @RequestParam int month) {
        return reportService.getMonthlyNetBalance(year, month);
    }
}
