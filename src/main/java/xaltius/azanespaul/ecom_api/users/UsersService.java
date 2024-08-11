package xaltius.azanespaul.ecom_api.users;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xaltius.azanespaul.ecom_api.customer.Customer;
import xaltius.azanespaul.ecom_api.customer.CustomerService;
import xaltius.azanespaul.ecom_api.seller.Seller;
import xaltius.azanespaul.ecom_api.seller.SellerService;
import xaltius.azanespaul.ecom_api.users.exception.UsersMobileAlreadyTakenException;
import xaltius.azanespaul.ecom_api.users.exception.UsersMobileNotFoundException;

@Service
@Transactional
public class UsersService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SellerService sellerService;

    private final PasswordEncoder passwordEncoder;

    public UsersService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Users saveUsersAsCustomer(Users users) {
        users.setRole("Customer");

        //Check if the username is already taken.
        usersRepository.findByMobile(users.getMobile())
                .ifPresent(data -> {
                    throw new UsersMobileAlreadyTakenException();
                });

        Users savedUsers = usersRepository.save(users);
        savedUsers.setPassword(passwordEncoder.encode(savedUsers.getPassword()));

        Customer newCustomer = new Customer();
        newCustomer.setUsers(savedUsers);
        customerService.saveCustomer(newCustomer);

        return savedUsers;
    }

    public Users saveUsersAsSeller(Users users) {
        users.setRole("Seller");

        //Check if the username is already taken.
        usersRepository.findByMobile(users.getMobile())
                .ifPresent(data -> {
                    throw new UsersMobileAlreadyTakenException();
                });

        Users savedUsers = usersRepository.save(users);
        savedUsers.setPassword(passwordEncoder.encode(savedUsers.getPassword()));

        Seller newSeller = new Seller();
        newSeller.setUsers(savedUsers);
        sellerService.saveSeller(newSeller);

        return savedUsers;
    }

    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        return usersRepository.findByMobile(mobile)
                .map(MyUserPrincipal::new)
                .orElseThrow(() -> new UsernameNotFoundException("mobile " + mobile + " is not found."));
    }

    public Users updateLoggedInUsers(Users u) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usersMobile = authentication.getName();

        Users users = usersRepository.findByMobile(usersMobile)
                .orElseThrow(() -> new UsersMobileNotFoundException(usersMobile));

        users.setName(u.getName());

        return usersRepository.save(users);
    }

    public Users updatePassword(Users u) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usersMobile = authentication.getName();

        Users users = usersRepository.findByMobile(usersMobile)
                .orElseThrow(() -> new UsersMobileNotFoundException(usersMobile));

        users.setPassword(passwordEncoder.encode(u.getPassword()));

        return usersRepository.save(users);
    }
}

