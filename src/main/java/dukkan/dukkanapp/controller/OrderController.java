package dukkan.dukkanapp.controller;

import dukkan.dukkanapp.model.Order;
import dukkan.dukkanapp.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    // Tüm siparişleri getir
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // ID ile sipariş getir
    @GetMapping("/{id}")
    public Optional<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    // Yeni sipariş ekle
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    // Sipariş sil
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
    @GetMapping("/search/customer")
    public List<Order> searchOrdersByCustomerName(@RequestParam String name) {
        return orderService.searchOrdersByCustomerName(name);
    }
    @GetMapping("/search/company")
    public List<Order> searchOrdersByCompanyName(@RequestParam String companyName) {
        return orderService.searchOrdersByCompanyName(companyName);
    }
    @GetMapping("/undelivered")
    public List<Order> getUndeliveredOrders() {
        return orderService.getUndeliveredOrders();
    }



}
