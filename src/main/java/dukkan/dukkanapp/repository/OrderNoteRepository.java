package dukkan.dukkanapp.repository;

import dukkan.dukkanapp.model.OrderNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderNoteRepository extends JpaRepository<OrderNote,Long> {
    List<OrderNote> findByOrderId(Long orderId);
}
