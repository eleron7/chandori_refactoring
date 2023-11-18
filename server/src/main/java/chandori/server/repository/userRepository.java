package chandori.server.repository;

import chandori.server.entity.user.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface userRepository extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findByUserId(String userId);
    Optional<UserAccount> findByEmail(String email);
}
