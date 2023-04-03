package dev.group12.books;

public class SubtotalBelowZeroException extends RuntimeException {

    public SubtotalBelowZeroException(String message) {
        super(message);
    }

    public SubtotalBelowZeroException(String message, Throwable cause) {
        super(message, cause);
    }
}
