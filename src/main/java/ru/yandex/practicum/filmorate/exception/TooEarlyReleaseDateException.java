package ru.yandex.practicum.filmorate.exception;

public class TooEarlyReleaseDateException extends RuntimeException {
    public TooEarlyReleaseDateException(String message) {
        super(message);
    }
}
