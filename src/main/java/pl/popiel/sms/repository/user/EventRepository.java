package pl.popiel.sms.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.popiel.sms.model.event.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
