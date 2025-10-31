package ru.yandex.practicum.filmorate.exception.user;

public class IncorrectLoginFormatException extends RuntimeException {
    public IncorrectLoginFormatException(String message) {
        super(message);
    }
}
