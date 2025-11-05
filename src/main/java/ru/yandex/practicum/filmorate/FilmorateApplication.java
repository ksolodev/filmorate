package ru.yandex.practicum.filmorate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class FilmorateApplication {

	public static void main(String[] args) {
        System.out.println(Optional.empty());
		SpringApplication.run(FilmorateApplication.class, args);
	}

}
