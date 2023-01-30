package connectly.assignment.review;

import connectly.assignment.AcceptanceTest;
import connectly.assignment.fixture.ProductFactory;
import connectly.assignment.fixture.ReviewFactory;
import connectly.assignment.fixture.UserFactory;
import connectly.assignment.product.ProductRepository;
import connectly.assignment.product.domain.Product;
import connectly.assignment.product.dto.ProductResponse;
import connectly.assignment.product.dto.ProductUpdateDetailRequest;
import connectly.assignment.product.dto.ProductUpdateRequest;
import connectly.assignment.review.domain.Review;
import connectly.assignment.review.dto.ReviewRequest;
import connectly.assignment.review.dto.ReviewResponse;
import connectly.assignment.review.dto.ReviewUpdateRequest;
import connectly.assignment.user.UserRepository;
import connectly.assignment.user.domain.User;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ReviewControllerTest extends AcceptanceTest {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("리뷰를 추가 할 수 있다.")
    void insert() {
        // given
        User user = userRepository.save(UserFactory.create("서정국"));
        Product product = productRepository.save(ProductFactory.create("상품 A"));

        // when
        insertProduct(ReviewFactory.createRequest("짱입니다.", user.getId(), product.getId()));

        // then
        Review review = reviewRepository.findAllByProductId(product.getId()).get(0);
        assertThat(review.getContent()).isEqualTo("짱입니다.");
    }

    @Test
    @DisplayName("특정 상품 id에 대한 전체 리뷰를 조회할 수 있다.")
    void findAll() {
        // given
        User user = userRepository.save(UserFactory.create("서정국"));
        Product product = productRepository.save(ProductFactory.create("상품 A"));
        Review review = reviewRepository.save(ReviewFactory.create("최악", user, product));

        // when
        List<ReviewResponse> responses = findAllReviews(product.getId());

        // then
        assertThat(responses).hasSize(1);
        assertThat(responses.get(0).getContent()).isEqualTo(review.getContent());
    }

    @Test
    @DisplayName("리뷰를 수정할 수 있다.")
    void update() {
        // when
        User user = userRepository.save(UserFactory.create("서정국"));
        Product product = productRepository.save(ProductFactory.create("상품 A"));
        Review review = reviewRepository.save(ReviewFactory.create("최악", user, product));

        // when
        updateReview(review.getId(), ReviewFactory.createUpdateRequest("최고에요!!"));

        // then
        Review updatedReview = reviewRepository.findById(review.getId()).get();
        assertThat(updatedReview.getContent()).isEqualTo("최고에요!!");
    }

    @Test
    @DisplayName("리뷰를 삭제할 수 있다.")
    void delete() {
        // given
        User user = userRepository.save(UserFactory.create("서정국"));
        Product product = productRepository.save(ProductFactory.create("상품 A"));
        Review review = reviewRepository.save(ReviewFactory.create("최악", user, product));

        // when
        deleteReview(review.getId());

        // then
        List<Review> reviews = reviewRepository.findAll();
        assertThat(reviews).hasSize(0);
    }

    private void insertProduct(ReviewRequest request) {
        RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when().post("/reviews")
                .then().log().all()
                .extract();
    }

    private void updateReview(Long id, ReviewUpdateRequest request) {
        RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when().patch("/reviews/" + id)
                .then().log().all()
                .extract();
    }

    private void deleteReview(Long id) {
        RestAssured
                .given().log().all()
                .when().delete("/reviews/" + id)
                .then().log().all()
                .extract();
    }

    private List<ReviewResponse> findAllReviews(Long productId) {
        return RestAssured
                .given().log().all()
                .when().get("/reviews?productId=" + productId)
                .then().log().all()
                .extract()
                .jsonPath().getList("data", ReviewResponse.class);
    }
}
