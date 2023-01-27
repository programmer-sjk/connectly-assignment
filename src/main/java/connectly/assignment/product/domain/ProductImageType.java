package connectly.assignment.product.domain;

public enum ProductImageType {
    MAIN("main"),
    DETAIL("detail");

    private final String value;

    ProductImageType(String value) {
        this.value = value;
    }
}
