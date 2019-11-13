package pl.popiel.sms.controller.v1.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.popiel.sms.controller.v1.api.form.PasswordForm;
import pl.popiel.sms.controller.v1.api.form.ProfileForm;
import pl.popiel.sms.dto.model.user.UserDto;
import pl.popiel.sms.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class DashboardController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/profile")
    public UserDto getUserProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.findUserByEmail(auth.getName());
        return userDto;
    }

    @PostMapping(value = "/profile")
    public UserDto updateProfile(@Valid @RequestBody ProfileForm profileForm, BindingResult bindingResult) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.findUserByEmail(auth.getName());
        if (!bindingResult.hasErrors()) {
            userDto.setFirstName(profileForm.getFirstName());
            userDto.setLastName(profileForm.getLastName());
            userDto.setMobileNumber(profileForm.getMobileNumber());
            userService.updateProfile(userDto);
        }
        return userDto;
    }

    @PostMapping(value = "/password")
    public UserDto changePassword(@Valid @RequestBody PasswordForm passwordForm, BindingResult bindingResult) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.findUserByEmail(auth.getName());
        if (!bindingResult.hasErrors()) {
            userService.changePassword(userDto, passwordForm.getPassword());
        }
        return userDto;
    }

}