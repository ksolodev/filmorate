package ru.yandex.practicum.filmorate.exception.user;

public class IncorrectEmailFormatException extends RuntimeException {
    public IncorrectEmailFormatException(String message) {
        super(message);
    }
}
