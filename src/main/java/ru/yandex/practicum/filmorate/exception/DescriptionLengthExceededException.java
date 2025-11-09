package ru.yandex.practicum.filmorate.exception;

public class DescriptionLengthExceededException extends RuntimeException {
    public DescriptionLengthExceededException(String message) {
        super(message);
    }
}
