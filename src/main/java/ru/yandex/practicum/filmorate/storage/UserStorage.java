package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserStorage {
    User add(User user);

    boolean delete(String login);

    Collection<User> getUsers();

    Optional<User> findByLogin(String login);

    Optional<User> findByEmail(String email);
}
