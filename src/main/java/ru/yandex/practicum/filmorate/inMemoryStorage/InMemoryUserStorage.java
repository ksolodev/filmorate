package ru.yandex.practicum.filmorate.inMemoryStorage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class InMemoryUserStorage implements UserStorage {
    private List<User> users = new ArrayList<>();

    @Override
    public User add(User user) {
        return null;
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public List<User> getUsers() {
        return List.of();
    }

    @Override
    public Optional<User> findByLogin(String login) {
        for (User u: users) {
            if (u.getLogin().equals(login)) {
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {

        return Optional.empty();
    }
}
