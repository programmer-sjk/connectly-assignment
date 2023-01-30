package connectly.assignment.review.dto;

import connectly.assignment.user.dto.UserRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ReviewRequestTest {
    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void init() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @DisplayName("리뷰 내용은 빈 값일 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    void validateContent(String input) {
        ReviewRequest request = new ReviewRequest(input, BigDecimal.ZERO, 1L, 1L);

        Set<ConstraintViolation<ReviewRequest>> violations = validator.validate(request);
        ConstraintViolation<ReviewRequest> violation = violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("content"))
                .findFirst()
                .get();

        assertThat(violation.getMessage()).isEqualTo("공백일 수 없습니다");
    }

    @Test
    @DisplayName("리뷰의 별점은 5 이하이다.")
    void validateStar() {
        ReviewRequest request = new ReviewRequest("content", BigDecimal.valueOf(6), 1L, 1L);

        Set<ConstraintViolation<ReviewRequest>> violations = validator.validate(request);
        ConstraintViolation<ReviewRequest> violation = violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("star"))
                .findFirst()
                .get();

        assertThat(violation.getMessage()).isEqualTo("5 이하여야 합니다");
    }
}
