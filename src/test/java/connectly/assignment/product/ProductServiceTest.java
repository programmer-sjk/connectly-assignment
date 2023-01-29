package connectly.assignment.product;

import connectly.assignment.fixture.ProductFactory;
import connectly.assignment.product.domain.Product;
import connectly.assignment.product.dto.ProductRequest;
import connectly.assignment.product.dto.ProductResponse;
import connectly.assignment.product.dto.ProductUpdateDetailRequest;
import connectly.assignment.product.dto.ProductUpdateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        this.productRepository.deleteAll();
    }

    @Test
    @DisplayName("특정 상품을 조회할 수 있다.")
    void find() {
        // given
        Product actual = productRepository.save(ProductFactory.create("구찌 가방 A"));

        // when
        ProductResponse result = productService.find(actual.getId());

        // then
        assertThat(actual.getId()).isEqualTo(result.getId());
        assertThat(actual.getName()).isEqualTo(result.getName());
    }

    @Test
    @DisplayName("전체 상품을 조회할 수 있다.")
    void findAll() {
        // given
        Product productA = productRepository.save(ProductFactory.create("구찌 가방 A"));
        Product productB = productRepository.save(ProductFactory.create("구찌 가방 B"));

        // when
        List<ProductResponse> results = productService.findAll();

        // then
        assertThat(results).hasSize(2);
        assertThat(results.get(0).getId()).isEqualTo(productA.getId());
        assertThat(results.get(1).getId()).isEqualTo(productB.getId());
    }

    @Test
    @DisplayName("상품을 추가할 수 있다.")
    void insert() {
        // given
        ProductRequest request = ProductFactory.createRequest("구찌 가방 A");

        // when
        productService.insertProduct(request);

        // then
        Product result = productRepository.findAll().get(0);
        assertThat(result.getName()).isEqualTo(request.getName());
    }

    @Test
    @DisplayName("상품 정보를 수정할 수 있다.")
    void update() {
        // given
        Product product = productRepository.save(ProductFactory.create("구찌 가방 A"));
        ProductUpdateRequest request = ProductFactory.createUpdateRequest("구찌 가방 B");

        // when
        productService.updateProduct(product.getId(), request);

        // then
        Product result = productRepository.findAll().get(0);
        assertThat(result.getName()).isEqualTo(request.getName());
    }

    @Test
    @DisplayName("상품 상세정보를 수정할 수 있다.")
    void updateDetail() {
        // given
        Product product = productRepository.save(ProductFactory.create("구찌 가방 A"));
        ProductUpdateDetailRequest request = new ProductUpdateDetailRequest("이 제품은 품절되었습니다.");

        // when
        productService.updateDetailProduct(product.getId(), request);

        // then
        Product result = productRepository.findAll().get(0);
        assertThat(result.getDetail()).isEqualTo("이 제품은 품절되었습니다.");
    }

    @Test
    @DisplayName("상품을 삭제할 수 있다.")
    void delete() {
        // given
        Product product = productRepository.save(ProductFactory.create("구찌 가방 A"));

        // when
        productService.deleteProduct(product.getId());

        // then
        List<Product> results = productRepository.findAll();
        assertThat(results).isEmpty();
    }
}
