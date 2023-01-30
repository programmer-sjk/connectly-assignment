package connectly.assignment.fixture;

import connectly.assignment.product.domain.Product;
import connectly.assignment.review.domain.Review;
import connectly.assignment.review.dto.ReviewRequest;
import connectly.assignment.review.dto.ReviewUpdateRequest;
import connectly.assignment.user.domain.User;
import connectly.assignment.user.dto.UserRequest;

import java.math.BigDecimal;

public class ReviewFactory {
    private ReviewFactory() {}

    public static Review create(String content, User user, Product product) {
        return new Review(content, BigDecimal.valueOf(4.5), user, product);
    }

    public static ReviewRequest createRequest(String content, Long userId, Long productId) {
        return new ReviewRequest(content, BigDecimal.valueOf(4.5), userId, productId);
    }

    public static ReviewUpdateRequest createUpdateRequest(String content) {
        return new ReviewUpdateRequest(content, BigDecimal.valueOf(4.5));
    }
}
