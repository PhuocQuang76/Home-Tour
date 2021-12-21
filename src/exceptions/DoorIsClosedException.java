package exceptions;

public class DoorIsClosedException extends Exception {
    public DoorIsClosedException() {
        super();
    }

    public DoorIsClosedException(String message) {
        super(message);
    }

    public DoorIsClosedException(String message, Throwable cause) {
        super(message, cause);
    }

    public DoorIsClosedException(Throwable cause) {
        super(cause);
    }

    protected DoorIsClosedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
