package connectly.assignment.product.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductUpdateRequestTest {
    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void init() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @DisplayName("브랜드는 빈 값일 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    void validateName(String input) {
        ProductUpdateRequest request = new ProductUpdateRequest.Builder()
                .brand(input)
                .build();

        Set<ConstraintViolation<ProductUpdateRequest>> violations = validator.validate(request);
        ConstraintViolation<ProductUpdateRequest> violation = violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("brand"))
                .findFirst()
                .get();

        assertThat(violation.getMessage()).isEqualTo("공백일 수 없습니다");
    }

    @DisplayName("제품 상태는 빈 값일 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    void validateProductStatus(String input) {
        ProductUpdateRequest request = new ProductUpdateRequest.Builder()
                .productStatus(input)
                .build();

        Set<ConstraintViolation<ProductUpdateRequest>> violations = validator.validate(request);
        ConstraintViolation<ProductUpdateRequest> violation = violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("productStatus"))
                .findFirst()
                .get();

        assertThat(violation.getMessage()).isEqualTo("공백일 수 없습니다");
    }
}
