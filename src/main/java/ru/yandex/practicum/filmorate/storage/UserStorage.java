package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;
import java.util.Optional;

public interface UserStorage {
    User add(User user);

    void delete(User user);

    List<User> getUsers();

    Optional<User> findByLogin(String login);

    Optional<User> findByEmail(String email);
}
