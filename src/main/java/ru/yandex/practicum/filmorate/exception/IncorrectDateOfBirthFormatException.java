package ru.yandex.practicum.filmorate.exception;

public class IncorrectDateOfBirthFormatException extends RuntimeException {
    public IncorrectDateOfBirthFormatException(String message) {
        super(message);
    }
}
