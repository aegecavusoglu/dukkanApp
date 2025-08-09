package dukkan.dukkanapp.controller;

import dukkan.dukkanapp.model.Reminder;
import dukkan.dukkanapp.service.ReminderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
public class ReminderController {
    private final ReminderService reminderService;

    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    @GetMapping
    public List<Reminder> getAllReminders() {
        return reminderService.getAllReminders();
    }

    @GetMapping("/{id}")
    public Reminder getReminderById(@PathVariable Long id) {
        return reminderService.getReminderById(id).orElseThrow();
    }

    @PostMapping
    public Reminder createReminder(@RequestBody Reminder reminder) {
        return reminderService.saveReminder(reminder);
    }

    @PostMapping("/{id}/done")
    public void markAsDone(@PathVariable Long id) {
        reminderService.markAsDone(id);
    }

    @GetMapping("/today")
    public List<Reminder> getActiveRemindersForToday() {
        return reminderService.getActiveRemindersForToday();
    }

    // Siparişe ait hatırlatmalar
    @GetMapping("/order/{orderId}")
    public List<Reminder> getRemindersByOrder(@PathVariable Long orderId) {
        return reminderService.getRemindersByOrderId(orderId);
    }

    // Ücret önden alındı mı/alınmadı mı filtrelemesi
    @GetMapping("/upfront")
    public List<Reminder> getRemindersByUpfrontPaid(@RequestParam Boolean upfrontPaid) {
        return reminderService.getRemindersByUpfrontPaid(upfrontPaid);
    }
}
