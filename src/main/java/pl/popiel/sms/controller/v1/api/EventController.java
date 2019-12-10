package pl.popiel.sms.controller.v1.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.popiel.sms.dto.response.Response;
import pl.popiel.sms.model.event.Event;
import pl.popiel.sms.repository.user.EventRepository;
import pl.popiel.sms.service.EventServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class EventController {

    @Autowired
    EventServiceImpl eventService;

    @Autowired
    EventRepository eventRepository;

    @PostMapping(value = "/event/join/{id}")
    public Response joinEvent (@PathVariable Long id){
        eventService.joinEvent(id);
        return Response.ok();
    }

    @PostMapping(value = "/event")
    public Response addEvent (@RequestBody Event event){
        eventService.addEvent(event);
        return Response.ok();
    }

    @GetMapping(value = "/event/{id}")
    public Optional<Event> getEvent (@PathVariable Long id){
        return eventService.getEvent(id);
    }

    @GetMapping(value = "/event")
    public List<Event> getEvents (){
        return eventService.getEvents();
    }

    @DeleteMapping(value = "/event/{id}")
    public ResponseEntity deleteEvents (@PathVariable Long id){
        eventService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
