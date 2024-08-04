package xaltius.azanespaul.ecom_api.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xaltius.azanespaul.ecom_api.security.AuthController;
import xaltius.azanespaul.ecom_api.system.Result;
import xaltius.azanespaul.ecom_api.users.Users;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Users> getAllCustomers() {
        return customerService.findAllCustomers();
    }

    @GetMapping("/customer/current")
    public Result getCurrentlyLoggedInCustomer() {
        return new Result("Currently logged in customer info", HttpStatus.OK.value(), customerService.findCurrentlyLoggedInCustomer());
    }
}
