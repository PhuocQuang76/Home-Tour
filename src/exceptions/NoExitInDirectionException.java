package exceptions;

public class NoExitInDirectionException extends Exception {
    public NoExitInDirectionException() {
    }

    public NoExitInDirectionException(String message) {
        super(message);
    }

    public NoExitInDirectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoExitInDirectionException(Throwable cause) {
        super(cause);
    }

    public NoExitInDirectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
