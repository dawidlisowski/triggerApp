package pl.dawidlisowski.someTriggerApp.models;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.dawidlisowski.someTriggerApp.models.entities.AlertEntity;
import pl.dawidlisowski.someTriggerApp.models.services.AlertService;
import pl.dawidlisowski.someTriggerApp.models.services.SmsService;
import pl.dawidlisowski.someTriggerApp.models.services.WeatherService;

@Component
@Log
public class AlertJob {

    final WeatherService weatherService;
    final AlertService alertService;
    final SmsService smsService;

    @Autowired
    public AlertJob(WeatherService weatherService, AlertService alertService, SmsService smsService) {
        this.weatherService = weatherService;
        this.alertService = alertService;
        this.smsService = smsService;
    }

    @Scheduled(initialDelay = 5000, fixedRate = 1000 * 60)
    public void checkWeatherAndAlerts() {
        for (AlertEntity alert : alertService.getAllAlerts()) {
            WeatherDto weather = weatherService.loadWeatherForCity(alert.getCity());
            boolean shouldSendSms =  isShouldSendSms(alert, weather);

            if (shouldSendSms) {
                sendSms(alert);
                log.info("Wysłano sms do: " + alert.getPhone());
            }
        }
//        System.out.println(weatherService.loadWeatherForCity("Kraków"));
//        log.warning(weatherService.loadWeatherForCity("Krakow").toString());
    }

    private static boolean isShouldSendSms(AlertEntity alert, WeatherDto weather) {
        boolean shouldSendSms = false;
        switch (alert.getTriggerType()){
            case EQUAL: {
                if ((int) weather.getTempDto().getTemperature() == alert.getTemperature()) {
                    shouldSendSms = true;
                }
                break;
            }
            case GREATER: {
                if ((int) weather.getTempDto().getTemperature() > alert.getTemperature()) {
                    shouldSendSms = true;
                }
                break;
            }
            case SMALLER: {
                if ((int) weather.getTempDto().getTemperature() < alert.getTemperature()) {
                    shouldSendSms = true;
                }
                break;
            }
        }
        return shouldSendSms;
    }

    private void sendSms(AlertEntity alertEntity) {
        smsService.sendSms(alertEntity.getPhone(), alertEntity.getMessage());
    }
}
