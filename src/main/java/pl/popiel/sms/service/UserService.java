package pl.popiel.sms.service;

import org.springframework.stereotype.Service;
import pl.popiel.sms.dto.model.user.UserDto;

@Service
public interface UserService {

    UserDto signup(UserDto userDto);

    UserDto findUserByEmail(String email);

    UserDto updateProfile(UserDto userDto);

    UserDto changePassword(UserDto userDto, String newPassword);
}