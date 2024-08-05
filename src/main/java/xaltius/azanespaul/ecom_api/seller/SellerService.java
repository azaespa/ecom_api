package xaltius.azanespaul.ecom_api.seller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
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
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private UsersRepository usersRepository;

    private final UsersToUsersDtoConverter usersToUsersDtoConverter;

    public SellerService(UsersToUsersDtoConverter usersToUsersDtoConverter) {
        this.usersToUsersDtoConverter = usersToUsersDtoConverter;
    }

    public void saveSeller(Seller seller) {
        sellerRepository.save(seller);
    }

    public List<Users> findAllSellers() {
        return usersRepository.findAllByRole("Seller");
    }

    public Users findUsersBySellerId(int sellerId) {
        Seller seller = findSellerBySellerId(sellerId);
        int sellerUsersId = seller.getUsers().getUsersId();

        return usersRepository.findById(sellerUsersId)
                .orElseThrow(() -> new SellerNotFoundException(Integer.toString(sellerId)));
    }

    public Seller findSellerByUsersId(int usersId) {
        return sellerRepository.findSellerByUsersId(usersId)
                .orElseThrow(() -> new UsersIdNotFoundException(Integer.toString(usersId)));
    }

    public Seller findSellerBySellerId(int sellerId) {
        return sellerRepository.findSellerBySellerId(sellerId)
                .orElseThrow(() -> new SellerNotFoundException(Integer.toString(sellerId)));
    }

    public Map<String, Object> findCurrentlyLoggedInSeller() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usersMobile = authentication.getName();

        Users users = usersRepository.findByMobile(usersMobile)
                .orElseThrow(() -> new UsersMobileNotFoundException(usersMobile));

        UsersDto usersDto = usersToUsersDtoConverter.convert(users);

        Map<String, Object> sellerMap = new HashMap<>();
        sellerMap.put("sellerInfo", usersDto);

        return sellerMap;
    }
}
