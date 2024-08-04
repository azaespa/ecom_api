package xaltius.azanespaul.ecom_api.users.exception;

public class UsersMobileAlreadyTakenException extends RuntimeException {
    public UsersMobileAlreadyTakenException() {
        super("Mobile number already taken.");
    }
}
