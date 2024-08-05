package xaltius.azanespaul.ecom_api.seller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {
    @Query("FROM Seller s where s.users.id = :usersId")
    Optional<Seller> findSellerByUsersId(@Param("usersId") int usersId);

    Optional<Seller> findSellerBySellerId(int sellerId);
}
