package xaltius.azanespaul.ecom_api.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM Cart WHERE customer_id = :customerId AND is_active = :isActive")
    List<Cart> findAllByCustomerIdAndStatus(@Param("customerId") int customerId, @Param("isActive") int isActive);

    @Modifying
    @Query("DELETE FROM Cart c where c.customer.id = :customerId")
    void deleteByCustomerId(@Param("customerId") int customerId);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE Cart SET is_active = 0 WHERE cart_id IN :cartIdList")
    void updateCartItemsStatus(@Param("cartIdList") List<Integer> cartIdList);
}
