package connectly.assignment.fixture;

import connectly.assignment.user.domain.User;
import connectly.assignment.user.dto.UserRequest;

public class UserFactory {
    private UserFactory() {}

    public static User create(String name) {
        return new User(name, "email@gmail.com", "password");
    }

    public static UserRequest createRequest(String name) {
        return new UserRequest(name, "email@gmail.com", "password");
    }
}
