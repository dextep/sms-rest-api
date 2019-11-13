package pl.popiel.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.popiel.sms.dto.mapper.UserMapper;
import pl.popiel.sms.dto.model.user.UserDto;
import pl.popiel.sms.exception.BRSException;
import pl.popiel.sms.exception.EntityType;
import pl.popiel.sms.exception.ExceptionType;
import pl.popiel.sms.model.user.Role;
import pl.popiel.sms.model.user.User;
import pl.popiel.sms.model.user.UserRoles;
import pl.popiel.sms.repository.user.RoleRepository;
import pl.popiel.sms.repository.user.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static pl.popiel.sms.exception.EntityType.USER;
import static pl.popiel.sms.exception.ExceptionType.DUPLICATE_ENTITY;
import static pl.popiel.sms.exception.ExceptionType.ENTITY_NOT_FOUND;


@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto signup(UserDto userDto) throws RuntimeException {
        Role userRole;
        User user = userRepository.findByEmail(userDto.getEmail());
        if ( user == null ) {
            if (userDto.isAdmin()) {
                userRole = roleRepository.findByRole(UserRoles.ADMIN.name());
            } else {
                userRole = roleRepository.findByRole(UserRoles.USER.name());
            }
            User newUser = new User();
            newUser.setEmail(userDto.getEmail());
            newUser.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            newUser.setRoles(new HashSet<>(Arrays.asList(userRole)));
            newUser.setFirstName(userDto.getFirstName());
            newUser.setLastName(userDto.getLastName());
            newUser.setMobileNumber(userDto.getMobileNumber());
            newUser.setBirthday(userDto.getBirthday());
            return UserMapper.toUserDto(userRepository.save(newUser));
        }
        throw exception(USER, DUPLICATE_ENTITY, userDto.getEmail());
    }

    public UserDto findUserByEmail(String email) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if (user.isPresent()) {
            UserDto userDto = new UserDto();
            userDto.setEmail(user.get().getEmail());
            userDto.setPassword(user.get().getPassword());
            userDto.setFirstName(user.get().getFirstName());
            userDto.setLastName(user.get().getLastName());
            userDto.setMobileNumber(user.get().getMobileNumber());
            userDto.setRoles(user.get().getRoles());//new HashSet<>(Arrays.asList(user.get().getRoles()))); //user.get().getRoles().stream().map(Role::getRole).collect(Collectors.toSet()));
            return userDto;
        }
        throw exception(USER, ENTITY_NOT_FOUND, email);
    }

    @Override
    public UserDto updateProfile(UserDto userDto) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));
        if (user.isPresent()) {
            User userModel = user.get();
            userModel.setFirstName(userDto.getFirstName());
            userModel.setLastName(userDto.getLastName());
            userModel.setMobileNumber(userDto.getMobileNumber());
            return UserMapper.toUserDto(userRepository.save(userModel));
        }
        throw exception(USER, ENTITY_NOT_FOUND, userDto.getEmail());
    }

    @Override
    public UserDto changePassword(UserDto userDto, String newPassword) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));
        if (user.isPresent()) {
            User userModel = user.get();
            userModel.setPassword(bCryptPasswordEncoder.encode(newPassword));
            return UserMapper.toUserDto(userRepository.save(userModel));
        }

        throw exception(USER, ENTITY_NOT_FOUND, userDto.getEmail());
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String args) {
        return BRSException.throwException(entityType,exceptionType,args);
    }
}
