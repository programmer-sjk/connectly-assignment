package com.product.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public class ReviewUpdateRequest {
    @NotBlank
    private String content;
    @Min(0) @Max(5)
    private BigDecimal star;

    private ReviewUpdateRequest() {}

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
