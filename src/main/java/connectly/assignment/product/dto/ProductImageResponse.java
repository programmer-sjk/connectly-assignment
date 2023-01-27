package connectly.assignment.product.dto;

import connectly.assignment.product.domain.ProductImage;

public class ProductImageResponse {
    private String path;
    private String type;

    public ProductImageResponse(ProductImage productImage) {
        this.path = productImage.getPath();
        this.type = productImage.getType().name();
    }

    public String getPath() {
        return path;
    }

    public String getType() {
        return type;
    }
}
