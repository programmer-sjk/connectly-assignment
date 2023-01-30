package connectly.assignment.product;

import connectly.assignment.product.domain.Product;
import connectly.assignment.product.domain.ProductImage;
import connectly.assignment.product.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    public ProductService(ProductRepository productRepository, ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
    }

    public ProductResponse find(Long id) {
        return new ProductResponse(this.findById(id));
    }

    public List<ProductAllResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductAllResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void insertProduct(ProductRequest request) {
        productRepository.save(request.toEntity());
    }

    @Transactional
    public void updateProduct(Long id, ProductUpdateRequest request) {
        Product product = this.findById(id);
        product.update(request.toEntity());
    }

    @Transactional
    public void updateDetailProduct(Long id, ProductUpdateDetailRequest request) {
        Product product = this.findById(id);
        product.updateDetail(request.getDetail());
    }

    @Transactional
    public void updateProductImages(Long id, List<ProductImageUpdateRequest> requests) {
        productImageRepository.deleteAllByProductId(id);

        Product product = findById(id);
        List<ProductImage> productImages = requests.stream()
                        .map(request -> request.toEntity(product))
                        .collect(Collectors.toList());

        productImageRepository.saveAll(productImages);
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    private Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다"));
    }
}
