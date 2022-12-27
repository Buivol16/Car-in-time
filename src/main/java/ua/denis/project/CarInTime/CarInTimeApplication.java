package ua.denis.project.CarInTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class CarInTimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarInTimeApplication.class, args);
	}




}
