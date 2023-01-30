package connectly.assignment.product.dto;

import connectly.assignment.product.domain.ProductImage;

public class ProductImageResponse {
    private String path;
    private String type;
    private int seq;

    public ProductImageResponse(ProductImage productImage) {
        this.path = productImage.getPath();
        this.type = productImage.getType().name();
        this.seq = productImage.getSeq();
    }

    public String getPath() {
        return path;
    }

    public String getType() {
        return type;
    }

    public int getSeq() {
        return seq;
    }
}
