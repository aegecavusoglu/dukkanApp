package dukkan.dukkanapp.service;

import dukkan.dukkanapp.model.OrderNote;
import dukkan.dukkanapp.repository.OrderNoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderNoteService {
    private final OrderNoteRepository orderNoteRepository;

    public OrderNoteService(OrderNoteRepository orderNoteRepository) {
        this.orderNoteRepository = orderNoteRepository;
    }

    public List<OrderNote> getNotesByOrderId(Long orderId) {
        return orderNoteRepository.findByOrderId(orderId);
    }

    public OrderNote saveNote(OrderNote note) {
        return orderNoteRepository.save(note);
    }

    public void deleteNote(Long id) {
        orderNoteRepository.deleteById(id);
    }

    public Optional<OrderNote> getNoteById(Long id) {
        return orderNoteRepository.findById(id);
    }
}
