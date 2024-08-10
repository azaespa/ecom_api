package xaltius.azanespaul.ecom_api.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    @Query(nativeQuery = true, value = "SELECT o.* FROM Orders o " +
                                        "INNER JOIN Cart c ON o.cart_id = c.cart_id " +
                                        "INNER JOIN Customer cu ON c.customer_id = cu.customer_id " +
                                        "INNER JOIN Users u ON cu.users_id = u.users_id " +
                                        "WHERE u.users_id = :usersId")
    List<Orders> findAllOrdersByCustomerUsersId(@Param("usersId") int usersId);

    @Query(nativeQuery = true, value = "SELECT o.* FROM Orders o " +
                                        "INNER JOIN Cart c ON o.cart_id = c.cart_id " +
                                        "INNER JOIN Product p ON c.product_id = p.product_id " +
                                        "INNER JOIN Seller s ON p.seller_id = s.seller_id " +
                                        "INNER JOIN Users u ON s.users_id = u.users_id " +
                                        "WHERE u.users_id = :usersId")
    List<Orders> findAllOrdersBySellerUsersId(@Param("usersId") int usersId);
}
