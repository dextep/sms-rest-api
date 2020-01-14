package pl.popiel.sms.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.popiel.sms.model.event.Event;
import pl.popiel.sms.model.event.EventType;

@Repository
public interface EventTypeRepository extends JpaRepository<EventType, Long> {
    EventType findByType (String type);
}
