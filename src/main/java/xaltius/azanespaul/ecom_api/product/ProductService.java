package xaltius.azanespaul.ecom_api.product;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import xaltius.azanespaul.ecom_api.product.exception.ProductInvalidSellerException;
import xaltius.azanespaul.ecom_api.product.exception.ProductNotFoundException;
import xaltius.azanespaul.ecom_api.seller.Seller;
import xaltius.azanespaul.ecom_api.seller.SellerService;
import xaltius.azanespaul.ecom_api.users.Users;
import xaltius.azanespaul.ecom_api.users.UsersRepository;
import xaltius.azanespaul.ecom_api.users.exception.UsersMobileNotFoundException;

import java.util.List;


@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private SellerService sellerService;

    public Product saveProduct(Product product) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usersMobile = authentication.getName();

        Users users = usersRepository.findByMobile(usersMobile)
                .orElseThrow(() -> new UsersMobileNotFoundException(usersMobile));

        Seller seller = sellerService.findSellerByUsersId(users.getUsersId());

        product.setSeller(seller);

        return productRepository.save(product);
    }

    public Product findProductById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(Integer.toString(id)));
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> findAllProductsByCategory(String category) {
        return productRepository.findAllByCategory(category);
    }

    public List<Product> findAllProductBySellerId(int sellerId) {
        sellerService.findSellerBySellerId(sellerId);

        return productRepository.findAllBySellerId(sellerId);
    }

    public Product updateProduct(@NotNull Product updatedProduct) {
        Product originalProduct = this.productRepository.findById(updatedProduct.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(Integer.toString(updatedProduct.getProductId())));

        originalProduct.setName(updatedProduct.getName());
        originalProduct.setDescription(updatedProduct.getDescription());
        originalProduct.setImageUrl(updatedProduct.getImageUrl());
        originalProduct.setCategory(updatedProduct.getCategory());
        originalProduct.setPrice(updatedProduct.getPrice());
        originalProduct.setQuantity(updatedProduct.getQuantity());

        return this.productRepository.save(originalProduct);
    }

    public Product updateProductQuantity(int productId, int updatedQuantity) {
        Product originalProduct = this.productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(Integer.toString(productId)));

        originalProduct.setQuantity(updatedQuantity);

        return this.productRepository.save(originalProduct);
    }

    public void deleteProductById(int productId) {
        this.productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(Integer.toString(productId)));

        this.productRepository.deleteById(productId);
    }
}
