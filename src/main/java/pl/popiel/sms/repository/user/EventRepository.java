package pl.popiel.sms.repository.user;

import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.popiel.sms.dto.model.event.EventDto;
import pl.popiel.sms.model.event.Event;
import pl.popiel.sms.model.user.User;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
import java.util.List;

@Repository

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query(value="select * from sms_events as e where e.experience > current_timestamp", nativeQuery = true)
//    @Query(value="select partners.*, (CASE WHEN partners.partner_id =:user_id THEN true ELSE false END) as user_exists from (select * from sms_events e left join sms_events_partner ep on (e.id = ep.event_id) where e.experience > current_timestamp) as partners ", nativeQuery = true)
//    @Query(value="select partners.* from (select * from sms_events e left join sms_events_partner ep on (e.id = ep.event_id) where e.experience > current_timestamp) as partners ", nativeQuery = true, resultClass=EventDto.class)
//    @Query(value="select partners.id, partners.availability, partners.description, partners.experience, partners.latitude, partners.longitude, partners.type, (CASE WHEN partners.partner_id =:user_id THEN true ELSE false END) as userExists" +
//            "from (select * from sms_events e left join sms_events_partner ep on (e.id = ep.event_id) where e.experience > current_timestamp) as partners", nativeQuery = true)
//    @Query(value = "select e from sms_events as e left join sms_events_partner ep on (e.id = ep.event_id) where e.experience > current_timestamp")
//    @Query(value = "SELECT e.*,(CASE WHEN e.partner_id =2 THEN true ELSE false END) as userExists  FROM sms_events e WHERE e.experience > current_timestamp", nativeQuery = true)
//    @Query(value="select partners.*, true as userExists from (select * from sms_events e left join sms_events_partner ep on (e.id = ep.event_id) where e.experience > current_timestamp) as partners ", nativeQuery = true)
    List<Event> getEventsBeforeExp();

    Event findEventById(Long id);

}