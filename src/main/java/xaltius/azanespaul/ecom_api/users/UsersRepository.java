package xaltius.azanespaul.ecom_api.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    List<Users> findAllByRole(String role);
    Optional<Users> findByMobile(String mobile);
}
