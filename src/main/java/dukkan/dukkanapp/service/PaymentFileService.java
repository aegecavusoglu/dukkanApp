package dukkan.dukkanapp.service;

import dukkan.dukkanapp.model.PaymentFile;
import dukkan.dukkanapp.repository.PaymentFileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentFileService {
    private final PaymentFileRepository paymentFileRepository;

    public PaymentFileService(PaymentFileRepository paymentFileRepository) {
        this.paymentFileRepository = paymentFileRepository;
    }

    public List<PaymentFile> getFilesByCashTransactionId(Long cashTransactionId) {
        return paymentFileRepository.findByCashTransactionId(cashTransactionId);
    }

    public PaymentFile save(PaymentFile paymentFile) {
        return paymentFileRepository.save(paymentFile);
    }

    public Optional<PaymentFile> getFileById(Long fileId) {
        return paymentFileRepository.findById(fileId);
    }
}
