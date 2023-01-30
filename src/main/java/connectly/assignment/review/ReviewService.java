package connectly.assignment.review;

import connectly.assignment.review.domain.Review;
import connectly.assignment.review.dto.ReviewRequest;
import connectly.assignment.user.UserRepository;
import connectly.assignment.user.domain.User;
import connectly.assignment.user.dto.UserRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void insertReview(ReviewRequest request) {
        User user = this.findUserById(request.getUserId());
        reviewRepository.save(request.toEntity(user));
    }

    private Review findById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 리뷰입니다."));
    }

    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
    }
}
