package pl.dawidlisowski.someTriggerApp.models.services;

import org.springframework.stereotype.Service;
import pl.dawidlisowski.someTriggerApp.models.entities.AlertEntity;
import pl.dawidlisowski.someTriggerApp.models.forms.AlertForm;
import pl.dawidlisowski.someTriggerApp.models.repositories.AlertRepository;

import java.util.List;

@Service
public class AlertService {

    final AlertRepository alertRepository;

    public AlertService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public List<AlertEntity> getAllAlerts() {
        return alertRepository.findAll();
    }

    public void saveAlert(AlertForm alertForm) {
        alertRepository.save(new AlertEntity(alertForm));
    }
}
