package xaltius.azanespaul.ecom_api.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xaltius.azanespaul.ecom_api.cart.dto.CartDto;
import xaltius.azanespaul.ecom_api.product.Product;
import xaltius.azanespaul.ecom_api.system.Result;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/cart/add")
    public Result saveItemToCart(@RequestBody Product product) {
        CartDto savedItem = cartService.saveItemToCart(product);
        return new Result("Save One Success", HttpStatus.OK.value(), savedItem);
    }
}
