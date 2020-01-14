package pl.popiel.sms.model.event;

import pl.popiel.sms.model.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="sms_event_types")
@SequenceGenerator(name = "sms_event_types_seq", sequenceName = "sms_event_types_seq", allocationSize = 1)
public class EventType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sms_event_types_seq")
    private long id;
    private String type;
    private String icon;

    public EventType () {

    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
