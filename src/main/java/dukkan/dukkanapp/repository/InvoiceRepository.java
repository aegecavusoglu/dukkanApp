package dukkan.dukkanapp.repository;

import dukkan.dukkanapp.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByDateBetween(LocalDate start, LocalDate end);
    List<Invoice> findByCompanyId(Long companyId);
    List<Invoice> findByCustomerId(Long customerId);
}
