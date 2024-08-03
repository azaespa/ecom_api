package xaltius.azanespaul.ecom_api.seller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xaltius.azanespaul.ecom_api.users.Users;
import xaltius.azanespaul.ecom_api.users.UsersRepository;

import java.util.List;

@Service
@Transactional
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private UsersRepository usersRepository;

    public void saveSeller(Seller seller) {
        sellerRepository.save(seller);
    }

    public List<Users> findAllSellers() {
        return usersRepository.findAllByRole("Seller");
    }
}
