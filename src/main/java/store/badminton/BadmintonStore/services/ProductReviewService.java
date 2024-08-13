package store.badminton.BadmintonStore.services;

import store.badminton.BadmintonStore.entities.ProductReview;

import java.util.List;

public interface ProductReviewService {
    ProductReview createProductReview(ProductReview productReview);

    void deleteProductReview(long id);

    List<ProductReview> getAllProductReviewByProductId(long productId);
}
