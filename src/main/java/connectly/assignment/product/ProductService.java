package connectly.assignment.product;

import connectly.assignment.product.domain.Product;
import connectly.assignment.product.dto.ProductRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product find(Long id) {
        return this.findById(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
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
