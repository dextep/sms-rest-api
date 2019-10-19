package pl.popiel.sms.controller.v1.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.popiel.sms.controller.v1.request.UserSignupRequest;
import pl.popiel.sms.dto.model.user.UserDto;
import pl.popiel.sms.dto.response.Response;
import pl.popiel.sms.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Handles the incoming POST API "/v1/user/signup"
     *
     * @param userSignupRequest
     * @return
     */
    @PostMapping("/signup")
    public Response signup(@RequestBody @Valid UserSignupRequest userSignupRequest) {
        return (Response) Response.ok().setPayload(registerUser(userSignupRequest, false));
    }

    /**
     * Register a new user in the database
     *
     * @param userSignupRequest
     * @return
     */
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