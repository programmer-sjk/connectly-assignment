package connectly.assignment.review.dto;

import connectly.assignment.product.domain.Product;
import connectly.assignment.review.domain.Review;
import connectly.assignment.user.domain.User;
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
