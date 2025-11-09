package ru.yandex.practicum.filmorate.storage.inMemoryStorage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.*;

@Component
public class InMemoryUserStorage implements UserStorage {
    private static final Map<String, User> users = new HashMap<>();

    @Override
    public User add(User user) {
        users.put(user.getLogin(), user);
        return user;
    }

    @Override
    public boolean delete(String login) {
        return users.remove(login, users.get(login));
    }

    @Override
    public Collection<User> getUsers() {
        return users.values();
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return Optional.ofNullable(users.get(login));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return users.values().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst();
    }
}
