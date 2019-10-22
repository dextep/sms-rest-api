package pl.popiel.sms;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import pl.popiel.sms.model.user.Role;
import pl.popiel.sms.model.user.User;
import pl.popiel.sms.repository.user.RoleRepository;
import pl.popiel.sms.repository.user.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories
public class SmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class, args);
    }

    @Bean
    CommandLineRunner init(RoleRepository roleRepository, UserRepository userRepository) {
        return args -> {
            //Create Admin and Passenger Roles
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
                admin.setPassword("$2y$12$sBofVIeLeT0m5dV9hBieIukQvjpb9GzRo5Q/xRTK7Ya7Dx.Lpiq4G"); // "123456"
                admin.setFirstName("John");
                admin.setLastName("Doe");
                admin.setMobileNumber("9425094250");
                admin.setRoles(new HashSet<>(Arrays.asList(adminRole)));
                userRepository.save(admin);
            }
        };
    }
}
