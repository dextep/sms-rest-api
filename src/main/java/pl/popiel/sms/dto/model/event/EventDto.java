package pl.popiel.sms.dto.model.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.Reference;
import org.springframework.data.repository.query.Param;
import pl.popiel.sms.model.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;


//@JsonInclude(value = JsonInclude.Include.NON_NULL)
//@JsonIgnoreProperties(ignoreUnknown = true)

public class EventDto {

    private Date experience;
    private String type;
    private String description;
    private int availability;
    private double latitude;
    private double longitude;

    public EventDto() {
    }

    public Date getExperience() {
        return experience;
    }

    public void setExperience(Date experience) {
        this.experience = experience;
    }

    public String getType() {
        return type;
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

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
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
}
