package store.badminton.BadmintonStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.badminton.BadmintonStore.entities.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
}
