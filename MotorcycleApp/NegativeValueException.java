package MotorcycleApp;

public class NegativeValueException extends Exception {
    public NegativeValueException() {
        super();
    }

    public NegativeValueException(String message) {
        super(message);
    }

    public NegativeValueException(String message, Throwable cause) {
        super(message, cause);
    }
}