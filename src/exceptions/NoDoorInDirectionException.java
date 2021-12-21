package exceptions;

public class NoDoorInDirectionException extends Exception {
    public NoDoorInDirectionException() {
    }

    public NoDoorInDirectionException(String message) {
        super(message);
    }

    public NoDoorInDirectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoDoorInDirectionException(Throwable cause) {
        super(cause);
    }

    public NoDoorInDirectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
