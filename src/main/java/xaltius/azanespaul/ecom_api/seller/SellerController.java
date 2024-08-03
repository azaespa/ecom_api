package xaltius.azanespaul.ecom_api.seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
