package ru.yandex.practicum.filmorate.exception;

public class IncorrectLoginFormatException extends RuntimeException {
    public IncorrectLoginFormatException(String message) {
        super(message);
    }
}
