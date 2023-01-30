package connectly.assignment.product;

import connectly.assignment.AcceptanceTest;
import connectly.assignment.fixture.ProductFactory;
import connectly.assignment.product.domain.Product;
import connectly.assignment.product.domain.ProductImage;
import connectly.assignment.product.dto.*;
import connectly.assignment.product.repository.ProductImageRepository;
import connectly.assignment.product.repository.ProductRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProductControllerTest extends AcceptanceTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    @Test
    @DisplayName("특정 상품을 조회할 수 있다.")
    void find() {
        // given
        productRepository.save(ProductFactory.create("루이비똥"));

        // when
        List<ProductResponse> responses = findAllProducts();

        // then
        assertThat(responses.get(0).getName()).isEqualTo("루이비똥");
    }

    @Test
    @DisplayName("전체 상품을 조회할 수 있다.")
    void findAll() {
        // given
        productRepository.save(ProductFactory.create("루이비똥"));
        productRepository.save(ProductFactory.create("샤넬"));

        // when
        List<ProductResponse> responses = findAllProducts();

        // then
        assertThat(responses).hasSize(2);
        assertThat(responses.get(0).getName()).isEqualTo("루이비똥");
        assertThat(responses.get(1).getName()).isEqualTo("샤넬");
    }

    @Test
    @DisplayName("상품을 추가할 수 있다.")
    void insert() {
        // when
        insertProduct(ProductFactory.createRequest("샤넬"));

        // then
        List<Product> products = productRepository.findAll();
        assertThat(products.get(0).getName()).isEqualTo("샤넬");
    }

    @Test
    @DisplayName("상품 정보를 수정할 수 있다.")
    void update() {
        // given
        Product product = productRepository.save(ProductFactory.create("루이비똥"));

        // when
        updateProduct(product.getId(), ProductFactory.createUpdateRequest("롤렉스"));

        // then
        List<Product> products = productRepository.findAll();
        assertThat(products.get(0).getName()).isEqualTo("롤렉스");
    }

    @Test
    @DisplayName("상품 상세정보를 수정할 수 있다.")
    void updateDetail() {
        // given
        Product product = productRepository.save(ProductFactory.create("루이비똥"));

        // when
        updateProductDetail(product.getId(), ProductFactory.createUpdateDetailRequest("상품 sold out"));

        // then
        List<Product> products = productRepository.findAll();
        assertThat(products.get(0).getDetail()).isEqualTo("상품 sold out");
    }

    @Test
    @DisplayName("상품 이미지를 수정할 수 있다.")
    void updateImages() {
        // given
        Product product = productRepository.save(ProductFactory.create("루이비똥"));

        // when
        updateProductImages(product.getId(), ProductFactory.createImageUpdateRequest("s3 image path"));

        // then
        ProductImage productImage = productImageRepository.findAll().get(0);
        assertThat(productImage.getPath()).isEqualTo("s3 image path");
    }

    @Test
    @DisplayName("상품을 삭제할 수 있다.")
    void delete() {
        // given
        Product product = productRepository.save(ProductFactory.create("루이비똥"));

        // when
        deleteProduct(product.getId());

        // then
        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(0);
    }

    private void insertProduct(ProductRequest request) {
        RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when().post("/products")
                .then().log().all()
                .extract();
    }

    private void updateProduct(Long id, ProductUpdateRequest request) {
        RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when().patch("/products/" + id)
                .then().log().all()
                .extract();
    }

    private void updateProductDetail(Long id, ProductUpdateDetailRequest request) {
        RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when().patch("/products/" + id + "/detail")
                .then().log().all()
                .extract();
    }

    private void updateProductImages(Long id, ProductImageUpdateRequest request) {
        RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(Arrays.asList(request))
                .when().patch("/products/" + id + "/images")
                .then().log().all()
                .extract();
    }

    private void deleteProduct(Long id) {
        RestAssured
                .given().log().all()
                .when().delete("/products/" + id)
                .then().log().all()
                .extract();
    }

    private List<ProductResponse> findAllProducts() {
        return RestAssured
                .given().log().all()
                .when().get("/products")
                .then().log().all()
                .extract()
                .jsonPath().getList("data", ProductResponse.class);
    }
}
