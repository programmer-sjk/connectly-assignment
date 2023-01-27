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

    @PostMapping()
    public void insertProduct(@RequestParam String name) {
        this.productService.insertProduct(name);
    }

    @PatchMapping("/{id}")
    public void updateProduct(@PathVariable Long id, @RequestParam String name) {
        this.productService.updateProduct(id, name);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        this.productService.deleteProduct(id);
    }
}
