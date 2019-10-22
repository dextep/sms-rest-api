package pl.popiel.sms.controller.v1.api.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PasswordForm {
    @NotBlank
    @Size(min = 5, max = 12)
    private String password;

    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
