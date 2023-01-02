package ua.denis.project.CarInTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@RestController
public class CarInTimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarInTimeApplication.class, args);
	}

	@GetMapping("/salam")
	public String salam(@RequestParam(value = "name", defaultValue = "world!") String second){
		return String.format("Salam %s", second);
	}


}
