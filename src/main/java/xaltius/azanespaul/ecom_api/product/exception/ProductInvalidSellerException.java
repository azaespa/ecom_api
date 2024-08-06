package xaltius.azanespaul.ecom_api.product.exception;

public class ProductInvalidSellerException extends RuntimeException {
    public ProductInvalidSellerException() {
        super("Seller id doesn't match.");
    }
}
