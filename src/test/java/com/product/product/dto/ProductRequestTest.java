package com.product.product.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductRequestTest {
    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void init() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @DisplayName("이름은 빈 값일 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    void validateName(String input) {
        ProductRequest request = new ProductRequest.Builder()
                .name(input)
                .build();

        Set<ConstraintViolation<ProductRequest>> violations = validator.validate(request);
        ConstraintViolation<ProductRequest> violation = violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("name"))
                .findFirst()
                .get();

        assertThat(violation.getMessage()).isEqualTo("공백일 수 없습니다");
    }

    @Test
    @DisplayName("원가는 0 이상이어야 한다.")
    void validateOriginPrice() {
        ProductRequest request = new ProductRequest.Builder()
                .originPrice(-1)
                .build();

        Set<ConstraintViolation<ProductRequest>> violations = validator.validate(request);
        ConstraintViolation<ProductRequest> violation = violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("originPrice"))
                .findFirst()
                .get();

        assertThat(violation.getMessage()).isEqualTo("0 이상이어야 합니다");
    }

    @Test
    @DisplayName("할인율은 100 보다 클 수 없다.")
    void validateDiscountRate() {
        ProductRequest request = new ProductRequest.Builder()
                .discountRate(101)
                .build();

        Set<ConstraintViolation<ProductRequest>> violations = validator.validate(request);
        ConstraintViolation<ProductRequest> violation = violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("discountRate"))
                .findFirst()
                .get();

        assertThat(violation.getMessage()).isEqualTo("100 이하여야 합니다");
    }

}
