package ru.yandex.practicum.filmorate.storage.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Component
public class FilmDbStorage implements FilmStorage {

    private final JdbcTemplate jdbcTemplate;

    public FilmDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Film add(Film film) {
        String sql = "INSERT INTO \"film\" (\"name\", \"release_date\", \"duration\", \"genre_id\", \"mpa_rating_id\") " +
                "VALUES (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement psCreator = con.prepareStatement(sql, new String[] {"id"});
            psCreator.setString(1, film.getName());
            psCreator.setDate(2, Date.valueOf(film.getReleaseDate()));
            psCreator.setInt(3, film.getDuration());
            psCreator.setInt(4, film.getGenreId());
            psCreator.setInt(5, film.getMpaRatingId());
            return psCreator;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key != null) {
            film.setId(key.longValue());
        }

        return film;
    }

    @Override
    public boolean delete(String name) {
        String sql = "DELETE FROM \"film\" WHERE \"name\" = ?";

        int rowsAffected = jdbcTemplate.update(sql, name);

        return rowsAffected > 0;
    }

    @Override
    public List<Film> getFilms() {

        String sql = "SELECT \"id\", \"name\", \"release_date\", \"duration\", \"genre_id\", \"mpa_rating_id\" FROM \"film\"";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Film film = new Film();
            film.setId(rs.getLong("id"));
            film.setName(rs.getString("name"));
            film.setReleaseDate(rs.getDate("release_date").toLocalDate());
            film.setDuration(rs.getInt("duration"));
            film.setGenreId(rs.getInt("genre_id"));
            film.setMpaRatingId(rs.getInt("mpa_rating_id"));
            return film;
        });
    }

    @Override
    public Optional<Film> find(String name) {

        String sql = "SELECT \"id\", \"name\", \"release_date\", \"duration\", \"genre_id\", \"mpa_rating_id\" FROM \"film\" WHERE \"name\" = ?";

        try {
            Film film = jdbcTemplate.queryForObject(sql, new Object[]{name}, (rs, rowNum) -> {
                Film f = new Film();
                f.setId(rs.getLong("id"));
                f.setName(rs.getString("name"));
                f.setReleaseDate(rs.getDate("release_date").toLocalDate());
                f.setDuration(rs.getInt("duration"));
                f.setGenreId(rs.getInt("genre_id"));
                f.setMpaRatingId(rs.getInt("mpa_rating_id"));
                return f;
            });
            return Optional.of(film);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
