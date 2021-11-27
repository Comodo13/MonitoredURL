package dmitri.korunovacni.urlmonitor.exception;

public class RequestFormatException extends Exception {

    public RequestFormatException(String message) {
        super(message);
    }

    public RequestFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
