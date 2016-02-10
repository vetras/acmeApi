package exception;

public class HerokuDatabaseUrlException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public HerokuDatabaseUrlException(String message, Throwable cause) {
        super(message, cause);
    }

    public HerokuDatabaseUrlException(String message) {
        super(message);
    }
}
