package connectly.assignment.product.dto;

import connectly.assignment.product.domain.Product;
import connectly.assignment.product.domain.ProductImage;

public class ProductImageRequest {
    private String path;
    private String type;

    public ProductImage toEntity(Product product) {
        return new ProductImage(path, type, product);
    }

    public String getPath() {
        return path;
    }

    public String getType() {
        return type;
    }
}
