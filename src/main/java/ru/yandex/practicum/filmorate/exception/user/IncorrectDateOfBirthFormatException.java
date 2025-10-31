package ru.yandex.practicum.filmorate.exception.user;

public class IncorrectDateOfBirthFormatException extends RuntimeException {
    public IncorrectDateOfBirthFormatException(String message) {
        super(message);
    }
}
