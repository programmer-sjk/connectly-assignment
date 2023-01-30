package connectly.assignment.review;

import connectly.assignment.review.domain.Review;
import connectly.assignment.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {}
