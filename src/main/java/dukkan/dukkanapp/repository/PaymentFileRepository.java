package dukkan.dukkanapp.repository;

import dukkan.dukkanapp.model.PaymentFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentFileRepository extends JpaRepository<PaymentFile, Long> {
    List<PaymentFile> findByCashTransactionId(Long cashTransactionId);
}
