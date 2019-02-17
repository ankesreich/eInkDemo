package de.ankesreich;

import java.util.logging.Logger;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class Application {

	Logger log = Logger.getLogger(Application.class.getName());
	
	@Bean
	public ExitCodeGenerator exitCodeGenerator() {
		log.info("exit");
		return () -> 0;
	}
	
	
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(Application.class);
		application.run();
	}

}