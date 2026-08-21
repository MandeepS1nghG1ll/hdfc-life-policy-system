package exception;

public class InvalidClaimException extends PolicyServiceException {
    public InvalidClaimException(String message) {
        super(message);
    }
}
