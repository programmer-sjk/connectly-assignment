package com.product.fixture;

import com.product.user.domain.User;
import com.product.user.dto.UserRequest;

public class UserFactory {
    private UserFactory() {}

    public static User create(String name) {
        return new User(name, "email@gmail.com", "password");
    }

    public static UserRequest createRequest(String name) {
        return new UserRequest(name, "email@gmail.com", "password");
    }
}
