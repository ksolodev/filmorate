package ru.yandex.practicum.filmorate.exception.film;

public class DescriptionLengthExceededException extends RuntimeException {
    public DescriptionLengthExceededException(String message) {
        super(message);
    }
}
