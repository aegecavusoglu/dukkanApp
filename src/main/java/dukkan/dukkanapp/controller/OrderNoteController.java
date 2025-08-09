package dukkan.dukkanapp.controller;

import dukkan.dukkanapp.model.Order;
import dukkan.dukkanapp.model.OrderNote;
import dukkan.dukkanapp.service.OrderNoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders/{orderId}/notes")
public class OrderNoteController {
    private final OrderNoteService orderNoteService;

    public OrderNoteController(OrderNoteService orderNoteService) {
        this.orderNoteService = orderNoteService;
    }

    @GetMapping
    public List<OrderNote> getNotes(@PathVariable Long orderId) {
        return orderNoteService.getNotesByOrderId(orderId);
    }

    @GetMapping("/{noteId}")
    public OrderNote getNoteById(@PathVariable Long noteId) {
        return orderNoteService.getNoteById(noteId).orElseThrow();
    }

    @PostMapping
    public OrderNote addNote(@PathVariable Long orderId, @RequestBody OrderNote note) {
        // İlgili sipariş bağlantısını kur
        Order order = new Order();
        order.setId(orderId);
        note.setOrder(order);
        return orderNoteService.saveNote(note);
    }

    @DeleteMapping("/{noteId}")
    public void deleteNote(@PathVariable Long noteId) {
        orderNoteService.deleteNote(noteId);
    }
}
