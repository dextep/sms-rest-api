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

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private BusReservationService busReservationService;
//
//    @Autowired
//    private ModelMapper modelMapper;

    @Override
    public UserDto signup(UserDto userDto) throws RuntimeException {
        Role userRole;
        Optional<User> user = userRepository.findByEmail(userDto.getEmail());
        if ( !user.isPresent() ) {
            if (userDto.isAdmin()) {
                userRole = roleRepository.findByRole(UserRoles.ADMIN.name());
            } else {
                userRole = roleRepository.findByRole(UserRoles.USER.name());
            }
            User newUser = new User();
            newUser.setEmail(userDto.getEmail());
            newUser.setPassword( userDto.getPassword());
            newUser.setRoles(new HashSet<>(Arrays.asList(userRole)));
            newUser.setFirstName(userDto.getFirstName());
            newUser.setLastName(userDto.getLastName());
            newUser.setMobileNumber(userDto.getMobileNumber());
            return UserMapper.toUserDto(userRepository.save(newUser));
        }
        throw exception(USER, DUPLICATE_ENTITY, userDto.getEmail());
    }

    /**
     * Search an existing user
     *
     * @param email
     * @return
     */
    public UserDto findUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
//            return modelMapper.map(user.get(), UserDto.class);
        }
        throw exception(USER, ENTITY_NOT_FOUND, email);
    }

    /**
     * Update User Profile
     *
     * @param userDto
     * @return
     */
    @Override
    public UserDto updateProfile(UserDto userDto) {
        Optional<User> user = userRepository.findByEmail(userDto.getEmail());
        if (user.isPresent()) {
            User userModel = user.get();
            userModel.setFirstName(userDto.getFirstName());
            userModel.setLastName(userDto.getLastName());
            userModel.setMobileNumber(userDto.getMobileNumber());
            return UserMapper.toUserDto(userRepository.save(userModel));
        }
        throw exception(USER, ENTITY_NOT_FOUND, userDto.getEmail());
    }

    /**
     * Change Password
     *
     * @param userDto
     * @param newPassword
     * @return
     */
    @Override
    public UserDto changePassword(UserDto userDto, String newPassword) {
        Optional<User> user = userRepository.findByEmail(userDto.getEmail());
        if (user.isPresent()) {
            User userModel = user.get();
            userModel.setPassword(newPassword);
            return UserMapper.toUserDto(userRepository.save(userModel));
        }

        throw exception(USER, ENTITY_NOT_FOUND, userDto.getEmail());
    }

    /**
     * Returns a new RuntimeException
     *
     * @param entityType
     * @param exceptionType
     * @param args
     * @return
     */
    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String args) {
        return BRSException.throwException(entityType,exceptionType,args);
    }
}
