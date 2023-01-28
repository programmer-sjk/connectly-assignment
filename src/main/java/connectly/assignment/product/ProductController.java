package connectly.assignment.product;

import connectly.assignment.common.ResponseMessage;
import connectly.assignment.product.dto.ProductRequest;
import connectly.assignment.product.dto.ProductResponse;
import connectly.assignment.product.dto.ProductUpdateDetailRequest;
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
    public ResponseEntity<ResponseMessage> getProduct(@PathVariable Long id) {
        ProductResponse response = this.productService.find(id);
        return ResponseEntity.ok(new ResponseMessage<>(response));
    }

    @GetMapping()
    public ResponseEntity<ResponseMessage> getAllProduct() {
        List<ProductResponse> responses = this.productService.findAll();
        return ResponseEntity.ok(new ResponseMessage(responses));
    }

    @PostMapping()
    public ResponseEntity<ResponseMessage> insertProduct(@RequestBody @Valid ProductRequest request) {
        this.productService.insertProduct(request);
        return ResponseEntity.ok(ResponseMessage.OK());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseMessage> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductUpdateRequest request
    ) {
        this.productService.updateProduct(id, request);
        return ResponseEntity.ok(ResponseMessage.OK());
    }

    @PatchMapping("/{id}/detail")
    public ResponseEntity<ResponseMessage> updateDetailProduct(
            @PathVariable Long id,
            @RequestBody ProductUpdateDetailRequest request
    ) {
        this.productService.updateDetailProduct(id, request);
        return ResponseEntity.ok(ResponseMessage.OK());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteProduct(@PathVariable Long id) {
        this.productService.deleteProduct(id);
        return ResponseEntity.ok(ResponseMessage.OK());
    }
}
