package dukkan.dukkanapp.repository;

import dukkan.dukkanapp.model.InvoiceFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceFileRepository extends JpaRepository<InvoiceFile, Long> {
    List<InvoiceFile> findByInvoiceId(Long invoiceId);
}
