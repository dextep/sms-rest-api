package pl.popiel.sms.model.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.Reference;
import org.springframework.data.repository.query.Param;
import pl.popiel.sms.dto.model.event.EventDto;
import pl.popiel.sms.model.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="sms_events")
@SequenceGenerator(name = "sms_events_seq", sequenceName = "sms_events_seq", allocationSize = 1)
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sms_event_seq")
    private long id;

    @NotNull
    @ManyToOne
    private User user;

    @ManyToMany
    private Set<User> partner = new HashSet<>();

    @ManyToOne
    private EventType type;

    private String description;
    private int availability;
    @NotNull
    private double latitude;
    @NotNull
    private double longitude;
    @NotNull
    private Date creationDate;
    private Date experience;
    private boolean status;
//    @Formula("select (CASE WHEN partners.partner_id =partners.user_id THEN true ELSE false END) from (select * from sms_events e left join sms_events_partner ep on (e.id = ep.event_id) where e.experience > current_timestamp) as partners")

    @Transient
    private boolean userExists;

    public Event() {
    }

    public boolean isUserExists() {
        return userExists;
    }

    public void setUserExists(boolean userExists) {
        this.userExists = userExists;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
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


    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }
}
