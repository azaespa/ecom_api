package xaltius.azanespaul.ecom_api.users.exception;

public class UsersAddressTypeException extends RuntimeException {
    public UsersAddressTypeException(String type) {
        super("Address with type " + type + " is not applicable.");
    }
}
