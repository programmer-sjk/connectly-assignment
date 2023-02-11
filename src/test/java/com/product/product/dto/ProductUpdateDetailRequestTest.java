package com.product.product.dto;

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

public class ProductUpdateDetailRequestTest {
    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void init() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @DisplayName("상품 상세정보는 빈 값일 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    void validateName(String input) {
        ProductUpdateDetailRequest request = new ProductUpdateDetailRequest(input);

        Set<ConstraintViolation<ProductUpdateDetailRequest>> violations = validator.validate(request);
        ConstraintViolation<ProductUpdateDetailRequest> violation = violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("detail"))
                .findFirst()
                .get();

        assertThat(violation.getMessage()).isEqualTo("공백일 수 없습니다");
    }
}
