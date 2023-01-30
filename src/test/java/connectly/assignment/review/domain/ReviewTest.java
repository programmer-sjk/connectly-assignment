package connectly.assignment.review.domain;

import connectly.assignment.fixture.ProductFactory;
import connectly.assignment.fixture.ReviewFactory;
import connectly.assignment.fixture.UserFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ReviewTest {
    @Test
    @DisplayName("review를 수정할 수 있다.")
    void update() {
        // given
        Review review = ReviewFactory.create(
                "최곱니다.",
                UserFactory.create("서정국"),
                ProductFactory.create("상품 A")
        );

        // when
        review.update("잘못보니 최악입니다.", BigDecimal.ZERO);

        // then
        assertThat(review.getContent()).isEqualTo("잘못보니 최악입니다.");
    }
}
