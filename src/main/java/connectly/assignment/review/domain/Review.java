package connectly.assignment.review.domain;

import connectly.assignment.common.BaseEntity;
import connectly.assignment.product.domain.Product;
import connectly.assignment.user.domain.User;
import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;

@Entity
public class Review extends BaseEntity {
    @Id @GeneratedValue
    private Long id;

    @Comment("리뷰 내용")
    @Lob
    private String content;

    @Comment("평점")
    @Column(precision = 2, scale = 1)
    private BigDecimal star = BigDecimal.valueOf(0.0);

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    protected Review() {}

    public Review(String content, BigDecimal star, User user, Product product) {
        this.content = content;
        this.star = star;
        this.user = user;
        product.addReview(this);
    }

    public void update(String content, BigDecimal star) {
        this.content = content;
        this.star = star;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public User getUser() {
        return user;
    }
}
