package store.badminton.BadmintonStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.badminton.BadmintonStore.entities.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
    public Cart findByUserId(Long userId);
}
