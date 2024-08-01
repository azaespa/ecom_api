package xaltius.azanespaul.ecom_api.customer;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}
