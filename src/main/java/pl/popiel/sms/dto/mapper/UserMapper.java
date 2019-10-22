package pl.popiel.sms.dto.mapper;

import org.springframework.stereotype.Component;
import pl.popiel.sms.dto.model.user.UserDto;
import pl.popiel.sms.model.user.User;

@Component
public class UserMapper {

    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setMobileNumber(user.getMobileNumber());
        userDto.setRoles(user.getRoles());
        return userDto;
    }

}
