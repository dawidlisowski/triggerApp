package pl.dawidlisowski.someTriggerApp.models.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.dawidlisowski.someTriggerApp.models.forms.AlertForm;

import javax.persistence.*;

@Entity
@Data
@Table(name = "alert")
@NoArgsConstructor
public class AlertEntity {
    public enum TriggerType{
        GREATER, SMALLER, EQUAL;
    }

    @Id @GeneratedValue
    private int id;
    private String phone;
    private String message;
    private int temperature;
    private String city;

    @Column(name = "char_trigger")
    @Enumerated(EnumType.STRING)
    private TriggerType triggerType;

    public AlertEntity(AlertForm alertForm) {
        this.message = alertForm.getMessage();
        this.phone = alertForm.getPhone();
        this.temperature = alertForm.getTemperature();
        this.city = alertForm.getCity();
        this.triggerType = alertForm.getTriggerType();
    }
}
