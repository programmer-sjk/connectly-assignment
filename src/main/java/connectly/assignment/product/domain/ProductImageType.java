package connectly.assignment.product.domain;

import java.util.Arrays;

public enum ProductImageType {
    MAIN("main"),
    DETAIL("detail");

    private final String value;

    ProductImageType(String value) {
        this.value = value;
    }

    public static ProductImageType find(String value) {
        return Arrays.stream(values())
                .filter(status -> status.value.equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 상품이미지 타입입니다."));
    }
}
