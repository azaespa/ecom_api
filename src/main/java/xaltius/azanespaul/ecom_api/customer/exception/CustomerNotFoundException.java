package xaltius.azanespaul.ecom_api.customer.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String customerId) {
        super("Could not find a seller with id " + customerId + ".");
    }
}
