package dukkan.dukkanapp.controller;

import dukkan.dukkanapp.model.Invoice;
import dukkan.dukkanapp.service.InvoiceService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @GetMapping("/{id}")
    public Invoice getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id).orElseThrow();
    }

    @PostMapping
    public Invoice createInvoice(@RequestBody Invoice invoice) {
        return invoiceService.saveInvoice(invoice);
    }

    @PutMapping("/{id}")
    public Invoice updateInvoice(@PathVariable Long id, @RequestBody Invoice invoice) {
        invoice.setId(id);
        return invoiceService.saveInvoice(invoice);
    }

    @DeleteMapping("/{id}")
    public void deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
    }

    @GetMapping("/date-range")
    public List<Invoice> getInvoicesByDateRange(
            @RequestParam("start") LocalDate start,
            @RequestParam("end") LocalDate end) {
        return invoiceService.getInvoicesByDateRange(start, end);
    }

    @GetMapping("/company/{companyId}")
    public List<Invoice> getInvoicesByCompany(@PathVariable Long companyId) {
        return invoiceService.getInvoicesByCompanyId(companyId);
    }

    @GetMapping("/customer/{customerId}")
    public List<Invoice> getInvoicesByCustomer(@PathVariable Long customerId) {
        return invoiceService.getInvoicesByCustomerId(customerId);
    }
}
