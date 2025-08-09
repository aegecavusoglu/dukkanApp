package dukkan.dukkanapp.service;

import dukkan.dukkanapp.model.OrderFile;
import dukkan.dukkanapp.repository.OrderFileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderFileService {
    private final OrderFileRepository orderFileRepository;

    public OrderFileService(OrderFileRepository orderFileRepository) {
        this.orderFileRepository = orderFileRepository;
    }

    public List<OrderFile> getFilesByOrderId(Long orderId) {
        return orderFileRepository.findByOrderId(orderId);
    }

    public OrderFile save(OrderFile orderFile) {
        return orderFileRepository.save(orderFile);
    }

    public Optional<OrderFile> getFileById(Long fileId) {
        return orderFileRepository.findById(fileId);
    }
}
