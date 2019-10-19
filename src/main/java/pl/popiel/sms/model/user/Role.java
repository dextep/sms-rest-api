package pl.popiel.sms.model.user;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sms_role")
public class Role {

    @Id
    private long id;

    @UniqueElements
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