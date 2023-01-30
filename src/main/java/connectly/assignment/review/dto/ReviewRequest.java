package connectly.assignment.review.dto;

import connectly.assignment.review.domain.Review;
import connectly.assignment.user.domain.User;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ReviewRequest {
    @NotBlank
    private String content;
    @Min(0) @Max(5)
    private BigDecimal start;
    @NotNull
    private Long userId;

    public Review toEntity(User user) {
        return new Review(content, start, user);
    }

    public String getContent() {
        return content;
    }

    public BigDecimal getStart() {
        return start;
    }

    public Long getUserId() {
        return userId;
    }
}
