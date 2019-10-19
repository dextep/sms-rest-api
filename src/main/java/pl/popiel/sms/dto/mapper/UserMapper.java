package pl.popiel.sms.dto.mapper;

import org.springframework.stereotype.Component;
import pl.popiel.sms.dto.model.user.RoleDto;
import pl.popiel.sms.dto.model.user.UserDto;
import pl.popiel.sms.model.user.User;

import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setMobileNumber(user.getMobileNumber());
//        userDto.setRoles(user.getRoles());
//                .setRoles(new HashSet<RoleDto>(user
//                        .getRoles()
//                        .stream()
//                        .map(role -> new ModelMapper().map(role, RoleDto.class))
//                        .collect(Collectors.toSet())));
        return userDto;
    }
}
