package dukkan.dukkanapp.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class OrderNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String note;                // Not metni
    private LocalDateTime noteDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;                // Hangi siparişe ait

    public OrderNote() {
    }

    public OrderNote(Long id, String note, LocalDateTime noteDate, Order order) {
        this.id = id;
        this.note = note;
        this.noteDate = noteDate;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(LocalDateTime noteDate) {
        this.noteDate = noteDate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
