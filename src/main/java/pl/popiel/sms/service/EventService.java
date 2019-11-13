package pl.popiel.sms.service;

import org.springframework.stereotype.Service;
import pl.popiel.sms.model.event.Event;

import java.util.List;
import java.util.Optional;

@Service
public interface EventService {

    Event addEvent(Event event);

    Optional<Event> getEvent(Long id);

    List<Event> getEvents();

    void deleteById(Long id);

    Event updateEvent(Event event);

    Event deleteEvent(Event event);

    Event acceptEvent(Event event);

}
