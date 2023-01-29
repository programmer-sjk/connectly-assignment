package connectly.assignment.user;

import connectly.assignment.common.ResponseMessage;
import connectly.assignment.product.ProductService;
import connectly.assignment.product.dto.*;
import connectly.assignment.user.dto.UserRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
