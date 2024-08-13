package store.badminton.BadmintonStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.badminton.BadmintonStore.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    User findUserByUsername(String username);
}
