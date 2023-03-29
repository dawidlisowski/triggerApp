package pl.dawidlisowski.someTriggerApp.models.forms;

import lombok.Data;
import pl.dawidlisowski.someTriggerApp.models.entities.AlertEntity;

import javax.validation.constraints.Pattern;

@Data
public class AlertForm {
    @Pattern(regexp = "\\d{9}")
    private String phone;
    @Pattern(regexp = "[a-zA-Z0-9]{160}")
    private String message;
    private int temperature;
    private String city;
    private AlertEntity.TriggerType triggerType;
}
