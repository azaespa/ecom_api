package xaltius.azanespaul.ecom_api.seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xaltius.azanespaul.ecom_api.system.Result;
import xaltius.azanespaul.ecom_api.users.Users;

import java.util.List;

@RestController
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @GetMapping("/sellers")
    public List<Users> getAllSellers() {
        return sellerService.findAllSellers();
    }

    @GetMapping("/seller/{sellerId}")
    public Users getUsersBySellerId(@PathVariable int sellerId) {
        return sellerService.findUsersBySellerId(sellerId);
    }

    @GetMapping("/seller/current")
    public Result getCurrentlyLoggedInSeller() {
        return new Result("Currently logged in seller info", HttpStatus.OK.value(), sellerService.findCurrentlyLoggedInSeller());
    }
}
