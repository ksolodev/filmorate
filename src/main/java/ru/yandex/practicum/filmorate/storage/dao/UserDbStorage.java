package ru.yandex.practicum.filmorate.storage.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class UserDbStorage implements UserStorage {

    private final JdbcTemplate jdbcTemplate;

    public UserDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User add(User user) {
        // SQL-запрос для вставки пользователя в таблицу "user"
        String sql = "INSERT INTO \"user\" (\"email\", \"login\", \"name\", \"birthday\") VALUES (?, ?, ?, ?)";

        // Выполнение запроса на добавление пользователя
        jdbcTemplate.update(sql, user.getEmail(), user.getLogin(), user.getName(), user.getBirthday());

        // Получение ID последнего вставленного пользователя
        String sqlSelectId = "SELECT \"id\" FROM \"user\" WHERE \"email\" = ?";
        Integer id = jdbcTemplate.queryForObject(sqlSelectId, new Object[]{user.getEmail()}, Integer.class);

        // Устанавливаем ID в объект пользователя
        user.setId(id);

        return user;
    }

    @Override
    public boolean delete(String login) {
        // SQL-запрос для удаления пользователя по ID
        String sql = "DELETE FROM \"user\" WHERE \"login\" = ?";
        int rowsAffected = jdbcTemplate.update(sql, login);

        // Если удалена хотя бы одна строка, возвращаем true
        return rowsAffected > 0;
    }

    @Override
    public Collection<User> getUsers() {
        // SQL-запрос для получения всех пользователей
        String sql = "SELECT \"id\", \"email\", \"login\", \"name\", \"birthday\" FROM \"user\"";

        // Выполнение запроса и маппинг результатов в список пользователей
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    @Override
    public Optional<User> findByLogin(String login) {
        // SQL-запрос для поиска пользователя по логину
        String sql = "SELECT \"id\", \"email\", \"login\", \"name\", \"birthday\" FROM \"user\" WHERE \"login\" = ?";

        // Попытка найти пользователя по логину
        List<User> users = jdbcTemplate.query(sql, new Object[]{login}, new UserRowMapper());
        return users.stream().findFirst();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        // SQL-запрос для поиска пользователя по email
        String sql = "SELECT \"id\", \"email\", \"login\", \"name\", \"birthday\" FROM \"user\" WHERE \"email\" = ?";

        // Попытка найти пользователя по email
        List<User> users = jdbcTemplate.query(sql, new Object[]{email}, new UserRowMapper());
        return users.stream().findFirst();
    }

    // Маппер для преобразования строки результата в объект User
    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
            user.setLogin(rs.getString("login"));
            user.setName(rs.getString("name"));
            user.setBirthday(rs.getTimestamp("birthday").toLocalDateTime());
            return user;
        }
    }
}
