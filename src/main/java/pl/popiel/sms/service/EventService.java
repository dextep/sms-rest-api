package pl.popiel.sms.service;

import org.springframework.stereotype.Service;
import pl.popiel.sms.dto.model.event.EventDto;
import pl.popiel.sms.model.event.Event;
import pl.popiel.sms.repository.user.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
public interface EventService {

    Event addEvent(Event event);

    Optional<Event> getEvent(Long id);

    List<Event> getEvents(Long id);

    void deleteById(Long id);

    Event updateEvent(Event event);

    Event deleteEvent(Event event);

    Event acceptEvent(Event event);

    void joinEvent(Long id);

    void leaveEvent(Long id);
}
