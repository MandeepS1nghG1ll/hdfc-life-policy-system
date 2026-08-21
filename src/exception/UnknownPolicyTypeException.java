package exception;

public class UnknownPolicyTypeException extends PolicyServiceException {
    public UnknownPolicyTypeException(String message) {
        super(message);
    }
}
