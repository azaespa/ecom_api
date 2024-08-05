package xaltius.azanespaul.ecom_api.seller.exception;

public class SellerNotFoundException extends RuntimeException {
    public SellerNotFoundException(String sellerId) {
        super("Could not find a seller with id " + sellerId + ".");
    }
}
