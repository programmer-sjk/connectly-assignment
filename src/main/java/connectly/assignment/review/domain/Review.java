package connectly.assignment.review.domain;

import connectly.assignment.common.BaseEntity;
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
    @Column(name = "createdBy")
    private User user;

    protected Review() {}

    public Review(String content, BigDecimal star, User user) {
        this.content = content;
        this.star = star;
        this.user = user;
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
