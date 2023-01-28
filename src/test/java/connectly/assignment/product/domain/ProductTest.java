package connectly.assignment.product.domain;

import connectly.assignment.fixture.ProductFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {
    @Test
    @DisplayName("상품 정보를 수정할 수 있다.")
    void update() {
        // given
        Product product = ProductFactory.create("상품 A");
        Product updatedProduct = ProductFactory.create("상품 B");

        // when
        product.update(updatedProduct);

        // then
        assertThat(product.getName()).isEqualTo("상품 B");
    }

    @Test
    @DisplayName("상품 상세 정보를 수정할 수 있다.")
    void updateDetail() {
        // given
        Product product = ProductFactory.create("상품 A");

        // when
        product.updateDetail("이 제품은 품절되었습니다.");

        // then
        assertThat(product.getDetail()).isEqualTo("이 제품은 품절되었습니다.");
    }
}
