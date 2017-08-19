package com.axonactive.kiss.TDD;

import com.axonactive.kiss.TDD.model.User;
import com.axonactive.kiss.TDD.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TddApplication {

	private static final Logger logger = LoggerFactory.getLogger(TddApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TddApplication.class, args);
	}

	@Bean
	public CommandLineRunner setup(UserRepository userRepository) {
		return (args) -> {
			userRepository.save(new User("Phan Vu Dinh", "Dai Loc -  Quang Nam"));
			userRepository.save(new User("Le Manh Chuc", "Hoi An - Quang Nam"));
			userRepository.save(new User("Thai Thi Hong", "Do Luong - Nghe An"));
			userRepository.save(new User("Huynh Dinh Than", "Que Song - Quang Nam"));
			logger.info("The sample data has been generated");
		};
	}
}
