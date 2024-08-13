package store.badminton.BadmintonStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.badminton.BadmintonStore.entities.ProductReview;

@Repository
public interface ProductReviewRepo extends JpaRepository<ProductReview, Long> {
}
