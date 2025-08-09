package dukkan.dukkanapp.repository;

import dukkan.dukkanapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer_FirstNameContainingIgnoreCaseOrCustomer_LastNameContainingIgnoreCase(String firstName, String lastName);
    List<Order> findByCustomer_Company_NameContainingIgnoreCase(String companyName);
    List<Order> findByStatusNot(String status);
}
