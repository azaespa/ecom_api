package xaltius.azanespaul.ecom_api.customer;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xaltius.azanespaul.ecom_api.users.Users;
import xaltius.azanespaul.ecom_api.users.UsersRepository;

import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UsersRepository usersRepository;

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public List<Users> findAllCustomers() {
        return usersRepository.findAllByRole("Customer");
    }
}
