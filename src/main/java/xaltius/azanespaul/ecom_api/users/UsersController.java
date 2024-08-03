package xaltius.azanespaul.ecom_api.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/register/customer")
    public Users saveUsersAsCustomer(@RequestBody Users users) {
        return usersService.saveUsersAsCustomer(users);
    }

    @PostMapping("/register/seller")
    public Users saveUsersAsSeller(@RequestBody Users users) {
        return usersService.saveUsersAsSeller(users);
    }
}

