package connectly.assignment.review;

import connectly.assignment.fixture.ProductFactory;
import connectly.assignment.fixture.ReviewFactory;
import connectly.assignment.fixture.UserFactory;
import connectly.assignment.product.ProductRepository;
import connectly.assignment.product.domain.Product;
import connectly.assignment.review.domain.Review;
import connectly.assignment.review.dto.ReviewRequest;
import connectly.assignment.review.dto.ReviewResponse;
import connectly.assignment.review.dto.ReviewUpdateRequest;
import connectly.assignment.user.UserRepository;
import connectly.assignment.user.UserService;
import connectly.assignment.user.domain.User;
import connectly.assignment.user.dto.UserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ReviewServiceTest {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        this.reviewRepository.deleteAll();
        this.userRepository.deleteAll();
        this.productRepository.deleteAll();
    }

    @Test
    @DisplayName("리뷰를 등록할 수 있다.")
    void insert() {
        // given
        User user = userRepository.save(UserFactory.create("서정국"));
        Product product = productRepository.save(ProductFactory.create("상품 A"));
        ReviewRequest request = ReviewFactory.createRequest("최고!!", user.getId(), product.getId());

        // when
        reviewService.insertReview(request);

        // then
        Review result = reviewRepository.findAll().get(0);
        assertThat(result.getContent()).isEqualTo(request.getContent());
    }

    @Test
    @DisplayName("상품 Id에 대한 전체 리뷰를 조회할 수 있다.")
    void findAllByProductId() {
        // given
        User user = userRepository.save(UserFactory.create("서정국"));
        Product product = productRepository.save(ProductFactory.create("상품 A"));
        Review review = reviewRepository.save(ReviewFactory.create("최고!!", user, product));

        // when
        List<ReviewResponse> responses = reviewService.findAllByProductId(product.getId());

        // then
        assertThat(responses).hasSize(1);
        assertThat(responses.get(0).getContent()).isEqualTo(review.getContent());
    }

    @Test
    @DisplayName("리뷰를 수정할 수 있다.")
    void update() {
        // given
        User user = userRepository.save(UserFactory.create("서정국"));
        Product product = productRepository.save(ProductFactory.create("상품 A"));
        Review review = reviewRepository.save(ReviewFactory.create("최고!!", user, product));
        ReviewUpdateRequest request = new ReviewUpdateRequest("아니다. 최악", BigDecimal.ZERO);

        // when
        reviewService.updateReview(review.getId(), request);

        // then
        Review updatedReview = reviewRepository.findById(review.getId()).get();
        assertThat(updatedReview.getContent()).isEqualTo(request.getContent());
    }

    @Test
    @DisplayName("리뷰를 삭제할 수 있다.")
    void delete() {
        // given
        User user = userRepository.save(UserFactory.create("서정국"));
        Product product = productRepository.save(ProductFactory.create("상품 A"));
        Review review = reviewRepository.save(ReviewFactory.create("최고!!", user, product));

        // when
        reviewService.deleteReview(review.getId());

        // then
        List<Review> reviews = reviewRepository.findAll();
        assertThat(reviews).isEmpty();
    }
}
