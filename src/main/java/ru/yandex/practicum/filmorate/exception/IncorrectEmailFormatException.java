package ru.yandex.practicum.filmorate.exception;

public class IncorrectEmailFormatException extends RuntimeException {
    public IncorrectEmailFormatException(String message) {
        super(message);
    }
}
