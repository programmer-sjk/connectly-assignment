package connectly.assignment.product;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @GetMapping("/{productId}")
    public String getProduct(@PathVariable Long productId) {
        return "get";
    }

    @GetMapping()
    public String getAllProduct() {
        return "get";
    }

    @PostMapping
    public String insertProduct() {
        return "post";
    }

    @PatchMapping("/{productId}")
    public String updateProduct(@PathVariable Long productId) {
        return "put " + productId;
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        return "delete " + productId;
    }
}
