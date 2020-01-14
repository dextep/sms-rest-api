package pl.popiel.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.popiel.sms.dto.model.event.EventDto;
import pl.popiel.sms.model.event.Event;
import pl.popiel.sms.model.user.User;
import pl.popiel.sms.repository.user.EventRepository;
import pl.popiel.sms.repository.user.EventTypeRepository;
import pl.popiel.sms.repository.user.UserRepository;

import java.util.*;

@Component
public class EventServiceImpl implements EventService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventTypeRepository eventTypeRepository;

    @Override
    public Event addEvent(EventDto event) {
        try{
            Event newEvent = new Event();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepository.findByEmail(auth.getName());

            newEvent.setUser(user);
            newEvent.setCreationDate(new Date());
            newEvent.setDescription(event.getDescription());
            newEvent.setAvailability(event.getAvailability());
            newEvent.setExperience(event.getExperience());
            newEvent.setLatitude(event.getLatitude());
            newEvent.setLongitude(event.getLongitude());
            newEvent.setType( eventTypeRepository.findByType(event.getType()));
            return eventRepository.save(newEvent);
        }catch ( Exception e){
            throw e;
        }
    }

    @Override
    public Optional<Event> getEvent(Long id){
        return eventRepository.findById(id);
    }

    @Override
    public List<Event> getEvents(Long id){
        return eventRepository.getEventsBeforeExp();
    }

    @Override
    public void deleteById(Long id){
        eventRepository.deleteById(id);
    }

    @Override
    public void joinEvent(Long eventId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        Event event = eventRepository.findEventById(eventId);
        event.getPartner().add(user);
        eventRepository.save(event);
    }

    @Override
    public void leaveEvent(Long eventId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        Event event = eventRepository.findEventById(eventId);
        event.getPartner().remove(user);
        eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return null;
    }

    @Override
    public Event deleteEvent(Event event) {
        return null;
    }

    @Override
    public Event acceptEvent(Event event) {
        return null;
    }


}
