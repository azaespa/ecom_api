package xaltius.azanespaul.ecom_api.users.exception;

public class UsersIdNotFoundException extends RuntimeException {
    public UsersIdNotFoundException(String id) {
        super("Could not find a user with id: " + id + ".");
    }
}
