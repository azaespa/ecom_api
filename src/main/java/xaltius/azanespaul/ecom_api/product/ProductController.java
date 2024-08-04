package xaltius.azanespaul.ecom_api.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xaltius.azanespaul.ecom_api.product.converter.ProductToProductDtoConverter;
import xaltius.azanespaul.ecom_api.product.dto.ProductDto;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    private final ProductToProductDtoConverter productToProductDtoConverter;

    public ProductController(ProductToProductDtoConverter productToProductDtoConverter) {
        this.productToProductDtoConverter = productToProductDtoConverter;
    }

    @PostMapping("/products")
    public ProductDto saveProduct(@RequestBody Product product){
        Product savedProduct = productService.saveProduct(product);
        ProductDto productDto = productToProductDtoConverter.convert(savedProduct);

        return productDto;
    }
}
