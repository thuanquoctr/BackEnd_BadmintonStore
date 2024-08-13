package store.badminton.BadmintonStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.badminton.BadmintonStore.entities.Role;
@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
