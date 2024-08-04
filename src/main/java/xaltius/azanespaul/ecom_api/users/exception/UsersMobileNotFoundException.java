package xaltius.azanespaul.ecom_api.users.exception;

public class UsersMobileNotFoundException extends RuntimeException {
    public UsersMobileNotFoundException(String mobile) {
        super("Could not find a user with mobile: " + mobile + ".");
    }
}
