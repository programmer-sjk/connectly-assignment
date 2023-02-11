package com.product.user;

import com.product.common.ResponseMessage;
import com.product.user.dto.UserRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseMessage<String> insertProduct(@RequestBody @Valid UserRequest request) {
        this.userService.insertUser(request);
        return ResponseMessage.ok();
    }
}
