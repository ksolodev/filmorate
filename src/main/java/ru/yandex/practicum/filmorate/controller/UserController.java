package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.user.IncorrectEmailFormatException;
import ru.yandex.practicum.filmorate.exception.user.IncorrectLoginFormatException;
import ru.yandex.practicum.filmorate.exception.user.UserAlreadyExistException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    private static final Map<String, User> users = new HashMap<>();

    @GetMapping
    public Collection<User> findAll() {
        return users.values();
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        checkUserOnException(user);
        useLoginIfNameIsEmpty(user);
        if (users.containsValue(user)) {
            log.error("Пользователь уже существует для: {}", user);
            throw new UserAlreadyExistException("Пользователь уже существует");
        }
        users.put(user.getEmail(), user);
        return user;
    }

    @PutMapping
    public User put(@Valid @RequestBody User user) {
        checkUserOnException(user);
        useLoginIfNameIsEmpty(user);
        users.put(user.getEmail(), user);
        return user;
    }

    private void checkUserOnException(User user) {
        if (user.getEmail().trim().isBlank() || !user.getEmail().contains("@")) {
            log.error("Электронная почта не может быть пустой" +
                    " и должна содержать символ '@' для: {}", user);
            throw new IncorrectEmailFormatException("Электронная почта не может быть пустой" +
                    " и должна содержать символ '@' для: {}");
        }
        if (user.getLogin().trim().isBlank()) {
            log.error("Логин не может быть пустым и содержать пробелы для: {}", user);
            throw new IncorrectLoginFormatException("Логин не может быть пустым и содержать пробелы");
        }
        if (user.getBirthday().isAfter(LocalDate.now())) {
            log.error("Дата рождения не может быть в будущем для: {}", user);    
        }
    }

    private void useLoginIfNameIsEmpty(User user) {
        if (user.getName() == null || user.getName().trim().isBlank()) {
            user.setName(user.getLogin());
        }
    }
}
