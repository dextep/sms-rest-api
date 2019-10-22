package pl.popiel.sms.model.user;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Entity
@Table(name="sms_users")
@SequenceGenerator(name = "sms_users_seq", sequenceName = "sms_users_seq", allocationSize = 1)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sms_users_seq")
    private long id;

    @Email
    private String email;

    private String password;

    private String firstName;

    private String lastName;

    @Column(name="mobile_nr")
    private String mobileNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public String getFullName() {
        return firstName != null ? firstName.concat(" ").concat(lastName) : "";
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
