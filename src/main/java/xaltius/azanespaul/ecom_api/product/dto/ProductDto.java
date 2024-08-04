package xaltius.azanespaul.ecom_api.product.dto;

public record ProductDto (
         int productId,
         String name,
         String description,
         String imageUrl,
         String category,
         int quantity,
         int price,
         int sellerId
        ){
}
