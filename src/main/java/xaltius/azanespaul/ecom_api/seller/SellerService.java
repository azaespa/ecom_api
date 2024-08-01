package xaltius.azanespaul.ecom_api.seller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    public void saveSeller(Seller seller) {
        sellerRepository.save(seller);
    }
}
