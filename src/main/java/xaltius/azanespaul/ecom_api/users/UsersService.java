package xaltius.azanespaul.ecom_api.users;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xaltius.azanespaul.ecom_api.customer.Customer;
import xaltius.azanespaul.ecom_api.customer.CustomerService;
import xaltius.azanespaul.ecom_api.seller.Seller;
import xaltius.azanespaul.ecom_api.seller.SellerService;

@Service
@Transactional
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SellerService sellerService;

    public Users saveUsersAsCustomer(Users users) {
        Users savedUsers = usersRepository.save(users);
        savedUsers.setRole("Customer");

        Customer newCustomer = new Customer();
        newCustomer.setUsers(savedUsers);
        customerService.saveCustomer(newCustomer);

        return savedUsers;
    }

    public Users saveUsersAsSeller(Users users) {
        Users savedUsers = usersRepository.save(users);
        savedUsers.setRole("Seller");

        Seller newSeller = new Seller();
        newSeller.setUsers(savedUsers);
        sellerService.saveSeller(newSeller);

        return savedUsers;
    }
}

