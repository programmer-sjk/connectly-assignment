package connectly.assignment.user.dto;

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

public class UserRequestTest {
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
        UserRequest request = new UserRequest(input, "email", "password");

        Set<ConstraintViolation<UserRequest>> violations = validator.validate(request);
        ConstraintViolation<UserRequest> violation = violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("name"))
                .findFirst()
                .get();

        assertThat(violation.getMessage()).isEqualTo("공백일 수 없습니다");
    }

    @DisplayName("이메일 형식이 아닐 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"test", "test@", "@gmail.com"})
    void validateEmail(String input) {
        UserRequest request = new UserRequest("name", input, "password");

        Set<ConstraintViolation<UserRequest>> violations = validator.validate(request);
        ConstraintViolation<UserRequest> violation = violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("email"))
                .findFirst()
                .get();

        assertThat(violation.getMessage()).isEqualTo("올바른 형식의 이메일 주소여야 합니다");
    }

    @DisplayName("패스워드는 빈 값일 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    void validatePassword(String input) {
        UserRequest request = new UserRequest("name", "email", input);

        Set<ConstraintViolation<UserRequest>> violations = validator.validate(request);
        ConstraintViolation<UserRequest> violation = violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("password"))
                .findFirst()
                .get();

        assertThat(violation.getMessage()).isEqualTo("공백일 수 없습니다");
    }
}
