package dukkan.dukkanapp.service;

import dukkan.dukkanapp.model.Reminder;
import dukkan.dukkanapp.repository.ReminderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReminderService {
    private final ReminderRepository reminderRepository;

    public ReminderService(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    public List<Reminder> getAllReminders() { return reminderRepository.findAll(); }

    public Reminder saveReminder(Reminder reminder) { return reminderRepository.save(reminder); }

    public void markAsDone(Long id) {
        Reminder r = reminderRepository.findById(id).orElseThrow();
        r.setDone(true);
        reminderRepository.save(r);
    }

    public List<Reminder> getActiveRemindersForToday() {
        return reminderRepository.findByDoneFalseAndReminderDateLessThanEqual(LocalDate.now());
    }

    public List<Reminder> getRemindersByOrderId(Long orderId) {
        return reminderRepository.findByOrderId(orderId);
    }

    public List<Reminder> getRemindersByUpfrontPaid(Boolean upfrontPaid) {
        return reminderRepository.findByUpfrontPaid(upfrontPaid);
    }

    public Optional<Reminder> getReminderById(Long id) {
        return reminderRepository.findById(id);
    }
}
