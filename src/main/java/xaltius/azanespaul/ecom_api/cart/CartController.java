package xaltius.azanespaul.ecom_api.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xaltius.azanespaul.ecom_api.cart.dto.CartDto;
import xaltius.azanespaul.ecom_api.product.Product;
import xaltius.azanespaul.ecom_api.system.Result;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/cart/add")
    public Result saveItemToCart(@RequestBody Product product) {
        CartDto savedItem = cartService.saveItemToCart(product);
        return new Result("Save One Success", HttpStatus.OK.value(), savedItem);
    }

    @GetMapping("/cart")
    public Result getAllItems() {
        List<CartDto> cartDtoList = cartService.findAll();
        return new Result("Find All Success", HttpStatus.OK.value(), cartDtoList);
    }

    @DeleteMapping("/cart")
    public Result deleteItem(@RequestBody Cart cart) {
        cartService.deleteItemFromCart(cart);
        return new Result("Delete One Success", HttpStatus.OK.value(), null);
    }

    @DeleteMapping("/cart/clear")
    public Result deleteAllItems() {
        cartService.deleteAllItemsFromCart();
        return new Result("Delete All Success", HttpStatus.OK.value(), null);
    }
}
