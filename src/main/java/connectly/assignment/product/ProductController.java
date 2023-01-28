package connectly.assignment.product;

import connectly.assignment.product.dto.ProductRequest;
import connectly.assignment.product.dto.ProductResponse;
import connectly.assignment.product.dto.ProductUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(this.productService.find(id));
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getAllProduct() {
        return ResponseEntity.ok(this.productService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Void> insertProduct(@RequestBody @Valid ProductRequest request) {
        this.productService.insertProduct(request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody ProductUpdateRequest request) {
        this.productService.updateProduct(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        this.productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
