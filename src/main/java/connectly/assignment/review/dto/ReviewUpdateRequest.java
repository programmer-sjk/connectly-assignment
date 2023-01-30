package connectly.assignment.review.dto;

import connectly.assignment.review.domain.Review;
import connectly.assignment.user.domain.User;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ReviewUpdateRequest {
    @NotBlank
    private String content;
    @Min(0) @Max(5)
    private BigDecimal star;

    protected ReviewUpdateRequest() {}

    public ReviewUpdateRequest(String content, BigDecimal star) {
        this.content = content;
        this.star = star;
    }

    public String getContent() {
        return content;
    }

    public BigDecimal getStar() {
        return star;
    }
}
