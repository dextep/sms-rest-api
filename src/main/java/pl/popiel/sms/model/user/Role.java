package pl.popiel.sms.model.user;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;

@Entity
@Table(name="sms_roles")
@SequenceGenerator(name = "sms_roles_seq", sequenceName = "sms_roles_seq", allocationSize = 1)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sms_roles_seq")
    private long id;

    private String role;

    public Role() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}