package dukkan.dukkanapp.repository;

import dukkan.dukkanapp.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder,Long> {
    List<Reminder> findByReminderDateBetween(LocalDate start, LocalDate end);
    List<Reminder> findByDoneFalseAndReminderDateLessThanEqual(LocalDate today);
    List<Reminder> findByOrderId(Long orderId); // Belirli siparişe ait hatırlatmalar
    List<Reminder> findByUpfrontPaid(Boolean upfrontPaid); // Ücreti önden alınan/alınmayanlar
}
