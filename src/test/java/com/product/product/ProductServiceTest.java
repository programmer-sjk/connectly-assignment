package com.product.product;

import com.product.product.dto.*;
import com.product.product.repository.ProductImageRepository;
import com.product.common.PageResponse;
import com.product.fixture.ProductFactory;
import com.product.product.domain.Product;
import com.product.product.domain.ProductImage;
import com.product.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    @BeforeEach
    void setUp() {
        this.productRepository.deleteAll();
        this.productImageRepository.deleteAll();
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
    @DisplayName("특정 상품을 조회시, display 값이 false라면 예외가 발생한다.")
    void findDisplayIsFalse() {
        // given
        Product notDisplayProduct = productRepository.save(ProductFactory.createNotDisplayProduct("구찌 가방 A"));

        // when & then
        assertThatThrownBy(() -> productService.find(notDisplayProduct.getId()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재하지 않는 상품입니다");
    }

    @Test
    @DisplayName("특정 상품이 존재하지 않을 경우 예외가 발생한다.")
    void findException() {
        // when & then
        assertThatThrownBy(() -> productService.find(999L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재하지 않는 상품입니다");
    }

    @Test
    @DisplayName("전체 상품을 조회할 수 있다.")
    void findAll() {
        // given
        Product productA = productRepository.save(ProductFactory.create("구찌 가방 A"));
        Product productB = productRepository.save(ProductFactory.create("구찌 가방 B"));


        // when
        PageResponse<List<ProductAllResponse>> responses = productService.findAll(PageRequest.of(0, 20));

        // then
        List<ProductAllResponse> results = responses.getData();
        assertThat(results).hasSize(2);
        assertThat(results.get(0).getId()).isEqualTo(productA.getId());
        assertThat(results.get(1).getId()).isEqualTo(productB.getId());
    }

    @Test
    @DisplayName("전체 상품 조회시, display 값이 false라면 조회되지 않는다.")
    void findAllWithDisplayIsFalse() {
        // given
        Product product = productRepository.save(ProductFactory.create("구찌 가방 A"));
        productRepository.save(ProductFactory.createNotDisplayProduct("구찌 가방 B"));


        // when
        PageResponse<List<ProductAllResponse>> responses = productService.findAll(PageRequest.of(0, 20));

        // then
        List<ProductAllResponse> results = responses.getData();
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getId()).isEqualTo(product.getId());
    }

    @Test
    @DisplayName("전체 상품 조회는 pagination 된다.")
    void pagination() {
        // given
        int paginationCount = 10;
        for (int i = 0; i < paginationCount + 1; i++) {
            productRepository.save(ProductFactory.create("구찌 가방" + i));
        }

        // when
        PageResponse<List<ProductAllResponse>> response = productService.findAll(PageRequest.of(0, 10));

        // then
        assertThat(response.getPaginationSize()).isEqualTo(paginationCount);
        assertThat(response.getTotalElements()).isEqualTo(paginationCount + 1);
        assertThat(response.getTotalPages()).isEqualTo(2);

        List<ProductAllResponse> results = response.getData();
        assertThat(results).hasSize(paginationCount);
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
    @DisplayName("상품 수정 시, 상품이 존재하지 않을 경우 예외가 발생한다.")
    void updateException() {
        // given
        ProductUpdateRequest request = ProductFactory.createUpdateRequest("구찌 가방 B");

        // when & then
        assertThatThrownBy(() -> productService.updateProduct(999L, request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재하지 않는 상품입니다");
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
    @DisplayName("상품 상세정보 수정시, 상품이 존재하지 않을 경우 예외가 발생한다.")
    void updateDetailException() {
        // given
        ProductUpdateDetailRequest request = new ProductUpdateDetailRequest("이 제품은 품절되었습니다.");

        // when & then
        assertThatThrownBy(() -> productService.updateDetailProduct(999L, request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재하지 않는 상품입니다");
    }

    @Test
    @DisplayName("상품 이미지를 수정할 수 있다.")
    void updateProductImages() {
        // given
        Product product = productRepository.save(ProductFactory.create("구찌 가방 A"));
        ProductImageUpdateRequest request = ProductFactory.createImageUpdateRequest("https://s3.amazone/xxxx");

        // when
        productService.updateProductImages(product.getId(), Arrays.asList(request));

        // then
        ProductImage result = productImageRepository.findAll().get(0);
        assertThat(result.getPath()).isEqualTo("https://s3.amazone/xxxx");
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
