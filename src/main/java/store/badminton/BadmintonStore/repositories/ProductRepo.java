package store.badminton.BadmintonStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.badminton.BadmintonStore.entities.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
}
