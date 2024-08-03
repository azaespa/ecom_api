package xaltius.azanespaul.ecom_api.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
