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

        return new Result("Save One Product Success", HttpStatus.OK.value(), productDto);
    }

    @GetMapping("/product/{productId}")
    public Result getProductById(@PathVariable int productId) {
        Product product = productService.findProductById(productId);
        ProductDto productDto = productToProductDtoConverter.convert(product);

        return new Result("Find One Product Success", HttpStatus.OK.value(), productDto);
    }

    @GetMapping("/products")
    public Result getAllProducts(){
        List<Product> productList = productService.findAllProducts();
        List<ProductDto> productDto = productList.stream().map(productToProductDtoConverter::convert).toList();

        return new Result("Find All Products Success", HttpStatus.OK.value(), productDto);
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

    @PutMapping("/products")
    public Result updateProduct(@RequestBody Product product) {
        Product updatedProduct = this.productService.updateProduct(product);
        ProductDto productDto = productToProductDtoConverter.convert(updatedProduct);

        return new Result("Update One Product Success", HttpStatus.OK.value(), productDto);
    }

    @PutMapping("/products/{productId}")
    public Result updateProductQuantity(@PathVariable int productId, @RequestBody Product product) {
        Product updatedProduct = this.productService.updateProductQuantity(productId, product.getQuantity());
        ProductDto productDto = productToProductDtoConverter.convert(updatedProduct);

        return new Result("Update One Product's Quantity Success", HttpStatus.OK.value(), productDto);
    }

    @DeleteMapping("/product/{productId}")
    public Result deleteProductById(@PathVariable int productId) {
        this.productService.deleteProductById(productId);
        return new Result("Delete One Product Success", HttpStatus.OK.value(), null);
    }
}
