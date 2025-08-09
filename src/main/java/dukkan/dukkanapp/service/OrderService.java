package dukkan.dukkanapp.service;

import dukkan.dukkanapp.model.Order;
import dukkan.dukkanapp.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
    public List<Order> searchOrdersByCustomerName(String name) {
        return orderRepository.findByCustomer_FirstNameContainingIgnoreCaseOrCustomer_LastNameContainingIgnoreCase(name, name);
    }
    public List<Order> searchOrdersByCompanyName(String companyName) {
        return orderRepository.findByCustomer_Company_NameContainingIgnoreCase(companyName);
    }
    public List<Order> getUndeliveredOrders() {
        return orderRepository.findByStatusNot("Tamamlandı");
    }



}
