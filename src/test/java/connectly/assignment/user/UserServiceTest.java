package connectly.assignment.user;

import connectly.assignment.fixture.UserFactory;
import connectly.assignment.user.domain.User;
import connectly.assignment.user.dto.UserRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("사용자를 등록할 수 있다.")
    void insert() {
        // given
        UserRequest request = UserFactory.createRequest("서정국");

        // when
        userService.insertUser(request);

        // then
        User result = userRepository.findAll().get(0);
        assertThat(result.getName()).isEqualTo(request.getName());
    }
}
