package com.product.review;

import com.product.product.domain.Product;
import com.product.product.repository.ProductRepository;
import com.product.review.domain.Review;
import com.product.review.dto.ReviewRequest;
import com.product.review.dto.ReviewResponse;
import com.product.review.dto.ReviewUpdateRequest;
import com.product.user.UserRepository;
import com.product.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ReviewService(
            ReviewRepository reviewRepository,
            UserRepository userRepository,
            ProductRepository productRepository
    ) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<ReviewResponse> findAllByProductId(Long productId) {
        Product product = this.findProductById(productId);
        return reviewRepository.findAllByProductId(product.getId())
                .stream()
                .map(ReviewResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void insertReview(ReviewRequest request) {
        User user = this.findUserById(request.getUserId());
        Product product = this.findProductById(request.getProductId());

        reviewRepository.save(request.toEntity(user, product));
    }

    @Transactional
    public void updateReview(Long id, ReviewUpdateRequest request) {
        Review review = this.findById(id);
        review.update(request.getContent(), request.getStar());
    }

    @Transactional
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    private Review findById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 리뷰입니다."));
    }

    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
    }

    private Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
    }
}
