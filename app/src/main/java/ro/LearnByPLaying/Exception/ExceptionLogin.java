package ro.LearnByPLaying.Exception;

/**
 * Created by Stefan on 4/20/2018.
 */

public class ExceptionLogin extends Exception {
    public ExceptionLogin() {
        super();
    }

    public ExceptionLogin(String message) {
        super(message);
    }

    public ExceptionLogin(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionLogin(Throwable cause) {
        super(cause);
    }
}
