package connectly.assignment.product.domain;

import java.util.Arrays;

public enum ProductStatus {
    NEW("new"),
    NORMAL("normal");

    private final String value;

    ProductStatus(String value) {
        this.value = value;
    }

    public static ProductStatus find(String value) {
        return Arrays.stream(values())
                .filter(status -> status.value.equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 상품 상태입니다."));
    }
}
