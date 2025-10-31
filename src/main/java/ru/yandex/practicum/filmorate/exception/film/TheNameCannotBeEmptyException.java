package ru.yandex.practicum.filmorate.exception.film;

public class TheNameCannotBeEmptyException extends RuntimeException {
    public TheNameCannotBeEmptyException(String message) {
        super(message);
    }
}
