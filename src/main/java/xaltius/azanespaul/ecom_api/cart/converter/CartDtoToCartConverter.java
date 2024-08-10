package xaltius.azanespaul.ecom_api.cart.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import xaltius.azanespaul.ecom_api.cart.Cart;
import xaltius.azanespaul.ecom_api.cart.dto.CartDto;
import xaltius.azanespaul.ecom_api.customer.CustomerService;
import xaltius.azanespaul.ecom_api.product.ProductService;

@Component
public class CartDtoToCartConverter implements Converter<CartDto, Cart> {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Override
    public Cart convert(CartDto source) {
        Cart cart = new Cart();
        cart.setCartId(source.cartId());
        cart.setCustomer(customerService.findCustomerByCustomerId(source.customerId()));
        cart.setProduct(productService.findProductById(source.productId()));

        return cart;
    }
}
