package connectly.assignment.user;


import connectly.assignment.AcceptanceTest;
import connectly.assignment.fixture.ProductFactory;
import connectly.assignment.fixture.UserFactory;
import connectly.assignment.product.ProductRepository;
import connectly.assignment.product.domain.Product;
import connectly.assignment.user.domain.User;
import connectly.assignment.user.dto.UserRequest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserControllerTest extends AcceptanceTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("사용자를 등록할 수 있다.")
    void insert() {
        // when
        insertUser(UserFactory.createRequest("서정국"));

        // then
        List<User> products = userRepository.findAll();
        assertThat(products.get(0).getName()).isEqualTo("서정국");
    }

    private void insertUser(UserRequest request) {
        RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when().post("/users")
                .then().log().all()
                .extract();
    }
}
