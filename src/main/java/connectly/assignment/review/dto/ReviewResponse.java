package connectly.assignment.review.dto;

import java.math.BigDecimal;

public class ReviewResponse {
    private Long id;
    private String content;
    private BigDecimal star;

    protected ReviewResponse() {}

    public ReviewResponse(Long id, String content, BigDecimal star) {
        this.id = id;
        this.content = content;
        this.star = star;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public BigDecimal getStar() {
        return star;
    }
}

