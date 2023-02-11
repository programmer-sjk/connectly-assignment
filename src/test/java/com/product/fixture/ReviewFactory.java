package com.product.fixture;

import com.product.review.domain.Review;
import com.product.product.domain.Product;
import com.product.review.dto.ReviewRequest;
import com.product.review.dto.ReviewUpdateRequest;
import com.product.user.domain.User;

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
