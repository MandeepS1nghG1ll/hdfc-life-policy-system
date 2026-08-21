package exception;

public class PolicyNotFoundException extends PolicyServiceException {
    public PolicyNotFoundException(String message) {
        super(message);
    }
}
