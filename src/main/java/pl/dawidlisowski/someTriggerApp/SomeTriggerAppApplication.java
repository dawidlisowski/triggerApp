package pl.dawidlisowski.someTriggerApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.dawidlisowski.someTriggerApp.models.services.WeatherService;

@SpringBootApplication
@EnableScheduling
public class SomeTriggerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SomeTriggerAppApplication.class, args);
	}

}
