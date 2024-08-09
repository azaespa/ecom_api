package xaltius.azanespaul.ecom_api.cart.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import xaltius.azanespaul.ecom_api.cart.Cart;
import xaltius.azanespaul.ecom_api.cart.dto.CartDto;

@Component
public class CartToCartDtoConverter implements Converter<Cart, CartDto> {
    @Override
    public CartDto convert(Cart source) {

        final CartDto cartDto = new CartDto(
                source.getCartId(),
                source.getCustomer().getCustomerId(),
                source.getProduct().getProductId()
        );

        return cartDto;
    }
}
