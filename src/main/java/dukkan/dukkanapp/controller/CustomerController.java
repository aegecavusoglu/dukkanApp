package dukkan.dukkanapp.controller;

import dukkan.dukkanapp.model.Customer;
import dukkan.dukkanapp.repository.CustomerRepository;
import dukkan.dukkanapp.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    // Tüm müşterileri listele
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // ID ile müşteri getir
    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    // Yeni müşteri ekle
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    // Müşteri sil
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
    @GetMapping("/search/company")
    public List<Customer> searchCustomersByCompanyName(@RequestParam String companyName) {
        return customerService.searchCustomersByCompanyName(companyName);
    }
}
