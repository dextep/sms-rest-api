package pl.popiel.sms.model.event;

import org.springframework.data.annotation.Reference;
import pl.popiel.sms.model.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name="sms_events")
@SequenceGenerator(name = "sms_events_seq", sequenceName = "sms_events_seq", allocationSize = 1)
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sms_event_seq")
    private long id;

    @ManyToOne
    private User user;

    @ManyToMany
    private Set<User> partner;

    private String type;
    private String description;
    private int seatsNumber;
    @NotNull
    private double latitude;
    @NotNull
    private double longitude;
    @NotNull
    private Date creationDate;
    private Date experience;
    private boolean status;

    public Event() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<User> getPartner() {
        return partner;
    }

    public void setPartner(Set<User> partner) {
        this.partner = partner;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getExperience() {
        return experience;
    }

    public void setExperience(Date experience) {
        this.experience = experience;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public int getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }
}
