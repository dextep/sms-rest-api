package pl.popiel.sms.controller.v1.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.popiel.sms.controller.v1.request.UserSignupRequest;
import pl.popiel.sms.dto.model.user.UserDto;
import pl.popiel.sms.dto.response.Response;
import pl.popiel.sms.model.user.User;
import pl.popiel.sms.service.UserService;
import pl.popiel.sms.service.UserServiceImpl;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/signup")
    public Response signup(@RequestBody @Valid UserSignupRequest userSignupRequest) {
        registerUser(userSignupRequest, false);
        return Response.ok();
    }

    @GetMapping("/{email}")
    public UserDto getById(@PathVariable String email){
        return userService.findUserByEmail(email);
    }

    private UserDto registerUser(UserSignupRequest userSignupRequest, boolean isAdmin) {
        UserDto userDto = new UserDto();
        userDto.setEmail(userSignupRequest.getEmail());
        userDto.setPassword(userSignupRequest.getPassword());
        userDto.setFirstName(userSignupRequest.getFirstName());
        userDto.setLastName(userSignupRequest.getLastName());
        userDto.setMobileNumber(userSignupRequest.getMobileNumber());
        userDto.setAdmin(isAdmin);

        return userService.signup(userDto);
    }
}