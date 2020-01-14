package pl.popiel.sms.controller.v1.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.popiel.sms.dto.model.event.EventDto;
import pl.popiel.sms.dto.response.Response;
import pl.popiel.sms.model.event.Event;
import pl.popiel.sms.model.event.EventType;
import pl.popiel.sms.model.user.User;
import pl.popiel.sms.repository.user.EventRepository;
import pl.popiel.sms.repository.user.EventTypeRepository;
import pl.popiel.sms.repository.user.UserRepository;
import pl.popiel.sms.service.EventServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class EventController {

    @Autowired
    EventServiceImpl eventService;

    @Autowired
    EventTypeRepository eventTypeRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/event/join/{id}")
    public Response joinEvent (@PathVariable Long id){
        eventService.joinEvent(id);
        return Response.ok();
    }

    @PostMapping(value = "/event/leave/{id}")
    public Response leaveEvent (@PathVariable Long id){
        eventService.leaveEvent(id);
        return Response.ok();
    }

    @PostMapping(value = "/event")
    public Response addEvent (@RequestBody EventDto event){
        eventService.addEvent(event);
        return Response.ok();
    }

    @GetMapping(value = "/event/{id}")
    public Optional<Event> getEvent (@PathVariable Long id){
        return eventService.getEvent(id);
    }

    @GetMapping(value = "/event")
    public List getEvents (){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        List<Event> event = eventService.getEvents(user.getId());
        return event;
    }

    @GetMapping(value = "/event/types")
    public List getEvenTypes (){
        List<EventType> eventTypes = eventTypeRepository.findAll();
        return eventTypes;
    }

    @DeleteMapping(value = "/event/{id}")
    public ResponseEntity deleteEvents (@PathVariable Long id){
        eventService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
