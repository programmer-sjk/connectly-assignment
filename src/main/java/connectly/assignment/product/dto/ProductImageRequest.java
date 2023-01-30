package connectly.assignment.product.dto;

import connectly.assignment.product.domain.Product;
import connectly.assignment.product.domain.ProductImage;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ProductImageRequest {
    @NotBlank
    private String path;
    @NotBlank
    private String type;
    @NotNull @Min(0)
    private int seq;

    public ProductImage toEntity() {
        return new ProductImage(path, type, seq);
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
