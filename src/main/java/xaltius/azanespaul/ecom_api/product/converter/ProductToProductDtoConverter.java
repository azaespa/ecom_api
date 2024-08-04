package xaltius.azanespaul.ecom_api.product.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import xaltius.azanespaul.ecom_api.product.Product;
import xaltius.azanespaul.ecom_api.product.dto.ProductDto;

@Component
public class ProductToProductDtoConverter implements Converter<Product, ProductDto> {
    @Override
    public ProductDto convert(Product source) {

        final ProductDto productDto = new ProductDto(
                source.getProductId(),
                source.getName(),
                source.getDescription(),
                source.getImageUrl(),
                source.getCategory(),
                source.getQuantity(),
                source.getPrice(),
                source.getSeller().getSellerId());

        return productDto;
    }
}
