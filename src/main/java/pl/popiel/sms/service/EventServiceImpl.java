package pl.popiel.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.popiel.sms.model.event.Event;
import pl.popiel.sms.model.user.User;
import pl.popiel.sms.repository.user.EventRepository;
import pl.popiel.sms.repository.user.UserRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class EventServiceImpl implements EventService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event addEvent(Event event) {
        try{
            Event newEvent = new Event();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepository.findByEmail(auth.getName());

            newEvent.setUser(user);
            newEvent.setPartner(event.getPartner());
            newEvent.setCreationDate(new Date());
            newEvent.setDescription(event.getDescription());
            newEvent.setSeatsNumber(event.getSeatsNumber());
            newEvent.setExperience(event.getExperience());
            newEvent.setLatitude(event.getLatitude());
            newEvent.setLongitude(event.getLongitude());
            newEvent.setStatus(event.isStatus());
            newEvent.setType(event.getType());
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
    public List<Event> getEvents(){
        return eventRepository.findAll();
    }

    @Override
    public void deleteById(Long id){
        eventRepository.deleteById(id);
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
