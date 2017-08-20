package com.axonactive.kiss.TDD;

import com.axonactive.kiss.TDD.model.Book;
import com.axonactive.kiss.TDD.model.User;
import com.axonactive.kiss.TDD.repository.BookRepository;
import com.axonactive.kiss.TDD.repository.UserRepository;
import com.axonactive.kiss.TDD.service.BookService;
import com.axonactive.kiss.TDD.service.UserService;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class TddApplication {

	private static final Logger logger = LoggerFactory.getLogger(TddApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TddApplication.class, args);
	}

	@Bean
	public CommandLineRunner setup(UserService userService, BookService bookService) {
		return (args) -> {
			userService.saveUser(new User("Phan Vu Dinh", "Dai Loc -  Quang Nam"));
			userService.saveUser(new User("Le Manh Chuc", "Hoi An - Quang Nam"));
			userService.saveUser(new User("Thai Thi Hong", "Do Luong - Nghe An"));
			userService.saveUser(new User("Huynh Dinh Than", "Que Song - Quang Nam"));
			logger.info("The sample data has been generated");

			List<User> users = userService.getAllUser();

			Map<User, Integer> test = new HashMap<>();
			test.put(users.get(0), 40);
			test.put(users.get(1), 20);
			test.put(users.get(2), 10);
			test.put(users.get(3), 5);

			test.entrySet().stream().forEach(entry -> {
                for(int i=0; i< (Integer) entry.getValue(); i++){
                    String name = RandomStringUtils.randomAlphabetic(10);
                    String author = RandomStringUtils.randomAlphabetic(8);
                    bookService.saveBook(new Book(name, author, (User)entry.getKey()));
                }
            });
		};
	}
}
