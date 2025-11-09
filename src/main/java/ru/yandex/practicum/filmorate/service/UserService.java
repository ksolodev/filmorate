package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.IncorrectEmailFormatException;
import ru.yandex.practicum.filmorate.exception.IncorrectLoginFormatException;
import ru.yandex.practicum.filmorate.exception.UserAlreadyExistException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.time.LocalDateTime;
import java.util.Collection;

@Slf4j
@Service
public class UserService {

    private final UserStorage userStorage;

    public UserService(@Qualifier("userDbStorage") UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public Collection<User> findAll() {
        return userStorage.getUsers();
    }

    public User create(User user) {
        checkUserOnException(user);
        useLoginIfNameIsEmpty(user);
        if (userStorage.findByLogin(user.getLogin()).isPresent()) {
            log.error("Пользователь уже существует для: {}", user);
            throw new UserAlreadyExistException("Пользователь уже существует");
        }
        return userStorage.add(user);
    }

    public User put(User user) {
        checkUserOnException(user);
        useLoginIfNameIsEmpty(user);
        return userStorage.add(user);
    }

    private void checkUserOnException(User user) {
        if (user.getEmail().trim().isBlank() || !user.getEmail().contains("@")) {
            log.error("Электронная почта не может быть пустой" + " и должна содержать символ '@' для: {}", user);
            throw new IncorrectEmailFormatException("Электронная почта не может быть пустой" + " и должна содержать символ '@' для: {}");
        }
        if (user.getLogin().trim().isBlank()) {
            log.error("Логин не может быть пустым и содержать пробелы для: {}", user);
            throw new IncorrectLoginFormatException("Логин не может быть пустым и содержать пробелы");
        }
        if (user.getBirthday().isAfter(LocalDateTime.now())) {
            log.error("Дата рождения не может быть в будущем для: {}", user);
        }
    }

    private void useLoginIfNameIsEmpty(User user) {
        if (user.getName() == null || user.getName().trim().isBlank()) {
            user.setName(user.getLogin());
        }
    }
}