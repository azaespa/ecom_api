package xaltius.azanespaul.ecom_api.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByCategory(String category);

    @Query("FROM Product p where p.seller.id = :sellerId")
    List<Product> findAllBySellerId(@Param("sellerId") int sellerId);
}
