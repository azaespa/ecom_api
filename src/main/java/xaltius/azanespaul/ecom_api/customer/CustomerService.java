package xaltius.azanespaul.ecom_api.customer;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
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
