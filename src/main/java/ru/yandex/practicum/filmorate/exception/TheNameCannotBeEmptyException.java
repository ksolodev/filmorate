package ru.yandex.practicum.filmorate.exception;

public class TheNameCannotBeEmptyException extends RuntimeException {
    public TheNameCannotBeEmptyException(String message) {
        super(message);
    }
}
