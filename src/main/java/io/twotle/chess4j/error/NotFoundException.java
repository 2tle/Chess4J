package io.twotle.chess4j.error;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {

    }

    public NotFoundException(String msg) {
        super(msg);
    }
}
