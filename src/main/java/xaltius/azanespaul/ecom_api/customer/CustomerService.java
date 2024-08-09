package xaltius.azanespaul.ecom_api.customer;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import xaltius.azanespaul.ecom_api.customer.exception.CustomerNotFoundException;
import xaltius.azanespaul.ecom_api.seller.Seller;
import xaltius.azanespaul.ecom_api.seller.exception.SellerNotFoundException;
import xaltius.azanespaul.ecom_api.users.Users;
import xaltius.azanespaul.ecom_api.users.UsersRepository;
import xaltius.azanespaul.ecom_api.users.converter.UsersToUsersDtoConverter;
import xaltius.azanespaul.ecom_api.users.dto.UsersDto;
import xaltius.azanespaul.ecom_api.users.exception.UsersIdNotFoundException;
import xaltius.azanespaul.ecom_api.users.exception.UsersMobileNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersToUsersDtoConverter usersToUsersDtoConverter;

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public List<Users> findAllCustomers() {
        return usersRepository.findAllByRole("Customer");
    }

    public Users findUsersByCustomerId(int customerId) {
        Customer customer = findCustomerByCustomerId(customerId);
        int customerUsersId = customer.getUsers().getUsersId();

        return usersRepository.findById(customerUsersId)
                .orElseThrow(() -> new CustomerNotFoundException(Integer.toString(customerId)));
    }

    public Customer findCustomerByCustomerId(int customerId) {
        return customerRepository.findCustomerByCustomerId(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(Integer.toString(customerId)));
    }

    public Customer findCustomerByUsersId(int usersId) {
        return customerRepository.findCustomerByUsersId(usersId)
                .orElseThrow(() -> new CustomerNotFoundException(Integer.toString(usersId)));
    }

    public Map<String, Object> findCurrentlyLoggedInCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usersMobile = authentication.getName();

        Users users = usersRepository.findByMobile(usersMobile)
                .orElseThrow(() -> new UsersMobileNotFoundException(usersMobile));

        UsersDto usersDto = usersToUsersDtoConverter.convert(users);

        Map<String, Object> customerMap = new HashMap<>();
        customerMap.put("customerInfo", usersDto);

        return customerMap;
    }
}
