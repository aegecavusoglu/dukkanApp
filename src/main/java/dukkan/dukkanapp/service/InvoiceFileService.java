package dukkan.dukkanapp.service;

import dukkan.dukkanapp.model.InvoiceFile;
import dukkan.dukkanapp.repository.InvoiceFileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceFileService {
    private final InvoiceFileRepository invoiceFileRepository;

    public InvoiceFileService(InvoiceFileRepository invoiceFileRepository) {
        this.invoiceFileRepository = invoiceFileRepository;
    }

    public List<InvoiceFile> getFilesByInvoiceId(Long invoiceId) {
        return invoiceFileRepository.findByInvoiceId(invoiceId);
    }

    public InvoiceFile save(InvoiceFile invoiceFile) {
        return invoiceFileRepository.save(invoiceFile);
    }

    public Optional<InvoiceFile> getFileById(Long fileId) {
        return invoiceFileRepository.findById(fileId);
    }
}
