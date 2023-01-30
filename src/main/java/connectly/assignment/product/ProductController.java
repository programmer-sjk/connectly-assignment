package connectly.assignment.product;

import connectly.assignment.common.PageResponse;
import connectly.assignment.common.ResponseMessage;
import connectly.assignment.product.domain.Product;
import connectly.assignment.product.dto.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public PageResponse<List<ProductAllResponse>> getAllProduct(Pageable pageable) {
        return this.productService.findAll(pageable);
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

    @PatchMapping("/{id}/images")
    public ResponseMessage<String> updateProductImages(
            @PathVariable Long id,
            @RequestBody List<ProductImageUpdateRequest> requests
    ) {
        this.productService.updateProductImages(id, requests);
        return ResponseMessage.ok();
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<String> deleteProduct(@PathVariable Long id) {
        this.productService.deleteProduct(id);
        return ResponseMessage.ok();
    }
}
