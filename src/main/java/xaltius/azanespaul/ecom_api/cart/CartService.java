package xaltius.azanespaul.ecom_api.cart;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import xaltius.azanespaul.ecom_api.cart.converter.CartToCartDtoConverter;
import xaltius.azanespaul.ecom_api.cart.dto.CartDto;
import xaltius.azanespaul.ecom_api.customer.Customer;
import xaltius.azanespaul.ecom_api.customer.CustomerService;
import xaltius.azanespaul.ecom_api.product.Product;
import xaltius.azanespaul.ecom_api.product.ProductService;
import xaltius.azanespaul.ecom_api.product.dto.ProductDto;
import xaltius.azanespaul.ecom_api.users.Users;
import xaltius.azanespaul.ecom_api.users.UsersRepository;
import xaltius.azanespaul.ecom_api.users.exception.UsersMobileNotFoundException;

import java.util.List;

@Service
@Transactional
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    private final CartToCartDtoConverter cartToCartDtoConverter;

    public CartService(CartToCartDtoConverter cartToCartDtoConverter) {
        this.cartToCartDtoConverter = cartToCartDtoConverter;
    }

    public CartDto saveItemToCart(Product p) {

        Product product = productService.findProductById(p.getProductId());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usersMobile = authentication.getName();

        Users users = usersRepository.findByMobile(usersMobile)
                .orElseThrow(() -> new UsersMobileNotFoundException(usersMobile));

        Customer customer = customerService.findCustomerByUsersId(users.getUsersId());

        Cart item = new Cart();
        item.setProduct(product);
        item.setCustomer(customer);

        Cart cart = cartRepository.save(item);
        CartDto cartDto = cartToCartDtoConverter.convert(cart);

        return cartDto;
    }

    public List<CartDto> findAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usersMobile = authentication.getName();

        Users users = usersRepository.findByMobile(usersMobile)
                .orElseThrow(() -> new UsersMobileNotFoundException(usersMobile));

        Customer customer = customerService.findCustomerByUsersId(users.getUsersId());

        List<Cart> cartList = cartRepository.findAllByCustomerId(customer.getCustomerId());

        List<CartDto> cartDtoList = cartList.stream().map(cartToCartDtoConverter::convert).toList();

        return cartDtoList;
    }

    public void deleteItemFromCart(Cart cart) {
        cartRepository.deleteById(cart.getCartId());
    }

    public void deleteAllItemsFromCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usersMobile = authentication.getName();

        Users users = usersRepository.findByMobile(usersMobile)
                .orElseThrow(() -> new UsersMobileNotFoundException(usersMobile));

        Customer customer = customerService.findCustomerByUsersId(users.getUsersId());
        cartRepository.deleteByCustomerId(customer.getCustomerId());
    }
}
