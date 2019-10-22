package pl.popiel.sms.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.popiel.sms.dto.model.user.UserDto;
import pl.popiel.sms.model.user.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}
