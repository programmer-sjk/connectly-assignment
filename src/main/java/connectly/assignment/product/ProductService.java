package connectly.assignment.product;

import connectly.assignment.product.domain.Product;
import connectly.assignment.product.dto.ProductRequest;
import connectly.assignment.product.dto.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse find(Long id) {
        ProductResponse productResponse = new ProductResponse(this.findById(id));
        System.out.println(productResponse.getId());
        return productResponse;
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
    }

    public void insertProduct(ProductRequest request) {
        productRepository.save(request.toEntity());
    }

    public void updateProduct(Long id,String name) {
        Product product = this.findById(id);
        product.setName(name);
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    private Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다, productId = " + id));
    }
}
