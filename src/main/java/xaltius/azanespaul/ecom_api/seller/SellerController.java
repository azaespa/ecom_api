package xaltius.azanespaul.ecom_api.seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xaltius.azanespaul.ecom_api.system.Result;
import xaltius.azanespaul.ecom_api.users.Users;
import xaltius.azanespaul.ecom_api.users.UsersService;

import java.util.List;

@RestController
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @Autowired
    private UsersService usersService;

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

    @PutMapping("/seller")
    public Result updateSellerName(@RequestBody Users users) {
        Users updatedUsers = usersService.updateLoggedInUsers(users);

        return new Result("Update Name Success", HttpStatus.OK.value(), updatedUsers);
    }

    @PutMapping("/seller/update/password")
    public Result updateSellerPassword(@RequestBody Users users) {
        Users updatedUsers = usersService.updatePassword(users);

        return new Result("Update Password Success", HttpStatus.OK.value(), updatedUsers);
    }

    @PutMapping("/seller/update/mobile")
    public Result updateMobile(@RequestBody Users users) {
        Users updatedUsers = sellerService.updateMobile(users);

        return new Result("Update Credentials Success", HttpStatus.OK.value(), updatedUsers);
    }
}
