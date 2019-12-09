package pl.popiel.sms.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.popiel.sms.model.event.Event;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value="select * from sms_events as e where e.experience > current_timestamp", nativeQuery = true)
    List<Event> getEventsBeforeExp();
}
