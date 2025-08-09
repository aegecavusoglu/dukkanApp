package dukkan.dukkanapp.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;                 // Hatırlatma metni
    private LocalDate reminderDate;         // Hangi gün hatırlatılsın
    private boolean done = false;           // Tamamlandı mı

    private Boolean upfrontPaid;            // Ücret önden alındı mı? (null, true, false)

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;                    // Hangi siparişle ilgili (opsiyonel, null olabilir)

    public Reminder() {}

    public Reminder(Long id, String message, LocalDate reminderDate, boolean done, Boolean upfrontPaid, Order order) {
        this.id = id;
        this.message = message;
        this.reminderDate = reminderDate;
        this.done = done;
        this.upfrontPaid = upfrontPaid;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(LocalDate reminderDate) {
        this.reminderDate = reminderDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Boolean getUpfrontPaid() {
        return upfrontPaid;
    }

    public void setUpfrontPaid(Boolean upfrontPaid) {
        this.upfrontPaid = upfrontPaid;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
