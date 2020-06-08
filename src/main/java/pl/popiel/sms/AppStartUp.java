package pl.popiel.sms;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.popiel.sms.model.event.Event;
import pl.popiel.sms.model.event.EventType;
import pl.popiel.sms.model.user.Role;
import pl.popiel.sms.model.user.User;
import pl.popiel.sms.repository.user.EventRepository;
import pl.popiel.sms.repository.user.EventTypeRepository;
import pl.popiel.sms.repository.user.RoleRepository;
import pl.popiel.sms.repository.user.UserRepository;

@Component
public class AppStartUp implements ApplicationListener<ApplicationReadyEvent> {

    RoleRepository roleRepository;
    UserRepository userRepository;
    EventRepository eventRepository;
    EventTypeRepository eventTypeRepository;

    @Autowired
    public AppStartUp (RoleRepository roleRepository, UserRepository userRepository, EventRepository eventRepository, EventTypeRepository eventTypeRepository){
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.eventTypeRepository = eventTypeRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        System.out.print("context refreshed event fired: ");

        Role adminRole = roleRepository.findByRole("ADMIN");
        if (adminRole == null) {
            adminRole = new Role();
            adminRole.setRole("ADMIN");
            roleRepository.save(adminRole);
        }

        Role userRole = roleRepository.findByRole("USER");
        if (userRole == null) {
            userRole = new Role();
            userRole.setRole("USER");
            roleRepository.save(userRole);
        }

        //Create an Admin user
        User adminUser = userRepository.findByEmail("admin@gmail.com");
        if ( adminUser == null) {
            User admin = new User();
            admin.setEmail("admin@gmail.com");
            admin.setPassword("$2y$12$mnmbE42x8XCNa8/fScbQe.Ua0yzOUXXq28SQ96rTpSrIgLPvawiaG"); // "admin"
            admin.setFirstName("John");
            admin.setLastName("Doe");
            admin.setMobileNumber("9425094250");
            admin.setRoles(new HashSet<>(Arrays.asList(adminRole)));
            userRepository.save(admin);
        }

        User newUser = userRepository.findByEmail("user@gmail.com");
        if ( newUser == null) {
            User user = new User();
            user.setEmail("user@gmail.com");
            user.setPassword("$2y$12$mnmbE42x8XCNa8/fScbQe.Ua0yzOUXXq28SQ96rTpSrIgLPvawiaG"); // "admin"
            user.setFirstName("Grzegorz");
            user.setLastName("Popiel");
            user.setMobileNumber("992881772");
            user.setRoles(new HashSet<>(Arrays.asList(adminRole)));
            userRepository.save(user);
        }

        EventType eventType1 = eventTypeRepository.findByType("swim");
        if (eventType1 == null) {

            EventType eventType = new EventType();
            eventType.setType("Walk");
            eventType.setIcon("🚶🏻‍️");
            eventTypeRepository.save(eventType);

            eventType = new EventType();
            eventType.setType("Run");
            eventType.setIcon("🏃🏼‍‍");
            eventTypeRepository.save(eventType);

            eventType = new EventType();
            eventType.setType("Cycle");
            eventType.setIcon("🚴‍️");
            eventTypeRepository.save(eventType);

            eventType = new EventType();
            eventType.setType("Swim");
            eventType.setIcon("🏊🏼‍️");
            eventTypeRepository.save(eventType);
        }

//        Event event = new Event();
//        event.setType(eventTypeRepository.findByType("swim"));
//        event.setDescription("description test");
//        User eventUser = new User();
//        eventUser.setId(2);
//        event.setUser(eventUser);
//        event.setCreationDate(new Date());
//        event.setExperience(new Date());
//        event.setLongitude(19.914377);
//        event.setLatitude(50.028783);
//        eventRepository.save(event);

        return;
    }
}
