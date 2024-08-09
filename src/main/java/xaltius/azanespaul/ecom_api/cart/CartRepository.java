package xaltius.azanespaul.ecom_api.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query("FROM Cart c where c.customer.id = :customerId")
    List<Cart> findAllByCustomerId(@Param("customerId") int customerId);

    @Modifying
    @Query("DELETE FROM Cart c where c.customer.id = :customerId")
    void deleteByCustomerId(@Param("customerId") int customerId);
}
