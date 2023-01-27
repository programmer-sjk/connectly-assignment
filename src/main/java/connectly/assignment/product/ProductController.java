package connectly.assignment.product;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return this.productService.find(id);
    }

    @GetMapping()
    public List<Product> getAllProduct() {
        return this.productService.findAll();
    }

    @PostMapping("/${name}")
    public String insertProduct(@PathVariable String name) {
        this.productService.insertProduct(name);
        return "post";
    }

    @PatchMapping("/{id}")
    public String updateProduct(@PathVariable Long id) {
        return "put " + id;
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return "delete " + id;
    }
}
