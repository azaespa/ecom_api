package xaltius.azanespaul.ecom_api.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findCustomerByCustomerId(int customerId);

    @Query("FROM Customer c where c.users.id = :usersId")
    Optional<Customer> findCustomerByUsersId(@Param("usersId") int usersId);
}
