package com.product.review.dto;

import com.product.product.domain.Product;
import com.product.review.domain.Review;
import com.product.user.domain.User;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ReviewRequest {
    @NotBlank
    private String content;
    @Min(0) @Max(5)
    private BigDecimal star;
    @NotNull
    private Long userId;
    @NotNull
    private Long productId;

    protected ReviewRequest() {}

    public ReviewRequest(String content, BigDecimal star, Long userId, Long productId) {
        this.content = content;
        this.star = star;
        this.userId = userId;
        this.productId = productId;
    }

    public Review toEntity(User user, Product product) {
        return new Review(content, star, user, product);
    }

    public String getContent() {
        return content;
    }

    public BigDecimal getStar() {
        return star;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getProductId() {
        return productId;
    }
}
