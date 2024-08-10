package xaltius.azanespaul.ecom_api.order;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import xaltius.azanespaul.ecom_api.cart.Cart;
import xaltius.azanespaul.ecom_api.cart.CartService;
import xaltius.azanespaul.ecom_api.cart.converter.CartDtoToCartConverter;
import xaltius.azanespaul.ecom_api.cart.dto.CartDto;
import xaltius.azanespaul.ecom_api.users.Users;
import xaltius.azanespaul.ecom_api.users.UsersRepository;
import xaltius.azanespaul.ecom_api.users.exception.UsersMobileNotFoundException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CartService cartService;

    private final CartDtoToCartConverter cartDtoToCartConverter;

    public OrdersService(CartDtoToCartConverter cartDtoToCartConverter) {
        this.cartDtoToCartConverter = cartDtoToCartConverter;
    }

    public List<Orders> saveAllOrders(List<CartDto> cartDtoList) {
        List<Cart> cartList = cartDtoList
                                .stream()
                                .map(cartDtoToCartConverter::convert)
                                .toList();

        String currentDateTime = new SimpleDateFormat("yyMMddHHmmssSSS")
                                .format(new Date());

        List<Orders> ordersList = new ArrayList<>();

        for (Cart cart: cartList) {
            Orders o = new Orders();
            o.setTransactionId(cart.getCustomer().getCustomerId() + currentDateTime);
            o.setDateOrdered(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            o.setCart(cart);
            o.setStatus("Pending");

            ordersList.add(o);
        }

        cartService.updateCartItemsToInactive(cartDtoList);

        return ordersRepository.saveAll(ordersList);
    }

    public List<Orders> findAllOrdersOfLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usersMobile = authentication.getName();

        Users users = usersRepository.findByMobile(usersMobile)
                .orElseThrow(() -> new UsersMobileNotFoundException(usersMobile));

        if (users.getRole().equals("Customer")) {
            return ordersRepository.findAllOrdersByCustomerUsersId(users.getUsersId());
        } else if (users.getRole().equals("Seller")) {
            return ordersRepository.findAllOrdersBySellerUsersId(users.getUsersId());
        }

        return null;
    }

    public List<Orders> findAllOrdersOfLoggedInUserByDate(String date) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usersMobile = authentication.getName();

        Users users = usersRepository.findByMobile(usersMobile)
                .orElseThrow(() -> new UsersMobileNotFoundException(usersMobile));

        if (users.getRole().equals("Customer")) {
            return ordersRepository
                    .findAllOrdersByCustomerUsersId(users.getUsersId())
                    .stream()
                    .filter(orders -> orders.getDateOrdered().equals(date))
                    .toList();
        } else if (users.getRole().equals("Seller")) {
            return ordersRepository.findAllOrdersBySellerUsersId(users.getUsersId())
                    .stream()
                    .filter(orders -> orders.getDateOrdered().equals(date))
                    .toList();
        }

        return null;
    }

    public List<Orders> findAllOrdersOfLoggedInUserById(int ordersId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usersMobile = authentication.getName();

        Users users = usersRepository.findByMobile(usersMobile)
                .orElseThrow(() -> new UsersMobileNotFoundException(usersMobile));

        if (users.getRole().equals("Customer")) {
            return ordersRepository
                    .findAllOrdersByCustomerUsersId(users.getUsersId())
                    .stream()
                    .filter(orders -> orders.getOrdersId() == ordersId)
                    .toList();
        } else if (users.getRole().equals("Seller")) {
            return ordersRepository.findAllOrdersBySellerUsersId(users.getUsersId())
                    .stream()
                    .filter(orders -> orders.getOrdersId() == ordersId)
                    .toList();
        }

        return null;
    }

    public Orders updateOrderById(int id, Orders o) {
        Orders orders = ordersRepository.findById(id).get();

        orders.setStatus(o.getStatus());

        return ordersRepository.save(orders);
    }

    public void deleteOrderById(int id) {
        ordersRepository.deleteById(id);
    }
}
