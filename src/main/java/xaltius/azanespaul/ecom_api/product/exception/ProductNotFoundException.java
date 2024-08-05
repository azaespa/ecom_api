package xaltius.azanespaul.ecom_api.product.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String id) {
        super("Could not find a product with id: " + id + ".");
    }
}
