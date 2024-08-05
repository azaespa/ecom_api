package xaltius.azanespaul.ecom_api.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xaltius.azanespaul.ecom_api.product.converter.ProductToProductDtoConverter;
import xaltius.azanespaul.ecom_api.product.dto.ProductDto;
import xaltius.azanespaul.ecom_api.system.Result;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    private final ProductToProductDtoConverter productToProductDtoConverter;

    public ProductController(ProductToProductDtoConverter productToProductDtoConverter) {
        this.productToProductDtoConverter = productToProductDtoConverter;
    }

    @PostMapping("/products")
    public Result saveProduct(@RequestBody Product product){
        Product savedProduct = productService.saveProduct(product);
        ProductDto productDto = productToProductDtoConverter.convert(savedProduct);

        return new Result("Save One Success", HttpStatus.OK.value(), productDto);
    }

    @GetMapping("/product/{id}")
    public Result getProductById(@PathVariable int id) {
        Product product = productService.findProductById(id);
        ProductDto productDto = productToProductDtoConverter.convert(product);

        return new Result("Find One Success", HttpStatus.OK.value(), productDto);
    }

    @GetMapping("/products")
    public Result getAllProducts(){
        List<Product> productList = productService.findAllProducts();
        List<ProductDto> productDto = productList.stream().map(productToProductDtoConverter::convert).toList();

        return new Result("Find All Success", HttpStatus.OK.value(), productDto);
    }

    @GetMapping("/products/{category}")
    public Result getAllProductsByCategory(@PathVariable String category){
        List<Product> productList = productService.findAllProductsByCategory(category);
        List<ProductDto> productDto = productList.stream().map(productToProductDtoConverter::convert).toList();

        return new Result("Find All Products by " + category + " Success", HttpStatus.OK.value(), productDto);
    }

    @GetMapping("/products/seller/{sellerId}")
    public Result getAllProductsBySellerId(@PathVariable int sellerId) {
        List<Product> productList = productService.findAllProductBySellerId(sellerId);
        List<ProductDto> productDto = productList.stream().map(productToProductDtoConverter::convert).toList();

        return new Result("Find All Products of " + sellerId + " Success", HttpStatus.OK.value(), productDto);
    }
}
