package xaltius.azanespaul.ecom_api.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import xaltius.azanespaul.ecom_api.order.Orders;
import xaltius.azanespaul.ecom_api.order.OrdersService;
import xaltius.azanespaul.ecom_api.security.AuthController;
import xaltius.azanespaul.ecom_api.system.Result;
import xaltius.azanespaul.ecom_api.users.Users;
import xaltius.azanespaul.ecom_api.users.UsersRepository;
import xaltius.azanespaul.ecom_api.users.UsersService;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private OrdersService ordersService;

    @GetMapping("/customers")
    public List<Users> getAllCustomers() {
        return customerService.findAllCustomers();
    }

    @GetMapping("/customer/current")
    public Result getCurrentlyLoggedInCustomer() {
        return new Result("Currently logged in customer info", HttpStatus.OK.value(), customerService.findCurrentlyLoggedInCustomer());
    }

    @PutMapping("/customer")
    public Result updateCustomerName(@RequestBody Users users) {
        Users updatedUsers = usersService.updateLoggedInUsers(users);

        return new Result("Update Name Success", HttpStatus.OK.value(), updatedUsers);
    }

    @PutMapping("/customer/update/password")
    public Result updateCustomerPassword(@RequestBody Users users) {
        Users updatedUsers = usersService.updatePassword(users);

        return new Result("Update Password Success", HttpStatus.OK.value(), updatedUsers);
    }

    @PutMapping("/customer/update/card")
    public Result updateCustomerCard(@RequestBody Users users) {
        Users updatedUsers = customerService.updateCreditCard(users);

        return new Result("Update Card Success", HttpStatus.OK.value(), updatedUsers);
    }

    @PutMapping("/customer/update/address")
    public Result updateCustomerAddress(@RequestParam("type") String type, @RequestBody Users users) {
        Users updatedUsers = customerService.updateAddress(users, type);

        return new Result("Update Address Success", HttpStatus.OK.value(), updatedUsers);
    }

    @PutMapping("/customer/update/credentials")
    public Result updateCredentials(@RequestBody Users users) {
        Users updatedUsers = customerService.updateCredentials(users);

        return new Result("Update Credentials Success", HttpStatus.OK.value(), updatedUsers);
    }

    @DeleteMapping("/customer/delete/address")
    public Result deleteAddress(@RequestParam("type") String type) {
        customerService.deleteAddress(type);

        return new Result("Delete Address Success", HttpStatus.OK.value(), null);
    }

    @GetMapping("/customer/orders")
    public Result getAllOrders() {
        List<Orders> ordersList = ordersService.findAllOrdersOfLoggedInUser();

        return new Result("Find All Success", HttpStatus.OK.value(), ordersList);
    }
}
