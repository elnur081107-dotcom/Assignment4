package model;

import exception.InvalidInputException;

public interface Validatable {

    void validate();

    default void validateNotNull(Object obj, String message) {
        if (obj == null) {
            throw new InvalidInputException(message);
        }
    }

    static void checkPositive(double value) {
        if (value <= 0) {
            throw new InvalidInputException("Value must be positive");
        }
    }
}


