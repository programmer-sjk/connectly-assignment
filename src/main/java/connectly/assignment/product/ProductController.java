package connectly.assignment.product;

import connectly.assignment.common.ResponseMessage;
import connectly.assignment.product.dto.ProductRequest;
import connectly.assignment.product.dto.ProductResponse;
import connectly.assignment.product.dto.ProductUpdateDetailRequest;
import connectly.assignment.product.dto.ProductUpdateRequest;
import jakarta.validation.Valid;
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
    public ResponseMessage<ProductResponse> getProduct(@PathVariable Long id) {
        ProductResponse response = this.productService.find(id);
        return new ResponseMessage<>(response);
    }

    @GetMapping()
    public ResponseMessage<List<ProductResponse>> getAllProduct() {
        List<ProductResponse> responses = this.productService.findAll();
        return new ResponseMessage<>(responses);
    }

    @PostMapping()
    public ResponseMessage<String> insertProduct(@RequestBody @Valid ProductRequest request) {
        this.productService.insertProduct(request);
        return ResponseMessage.ok();
    }

    @PatchMapping("/{id}")
    public ResponseMessage<String> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductUpdateRequest request
    ) {
        this.productService.updateProduct(id, request);
        return ResponseMessage.ok();
    }

    @PatchMapping("/{id}/detail")
    public ResponseMessage<String> updateDetailProduct(
            @PathVariable Long id,
            @RequestBody ProductUpdateDetailRequest request
    ) {
        this.productService.updateDetailProduct(id, request);
        return ResponseMessage.ok();
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<String> deleteProduct(@PathVariable Long id) {
        this.productService.deleteProduct(id);
        return ResponseMessage.ok();
    }
}
