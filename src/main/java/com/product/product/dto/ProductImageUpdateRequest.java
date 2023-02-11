package com.product.product.dto;

import com.product.product.domain.Product;
import com.product.product.domain.ProductImage;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductImageUpdateRequest {
    @NotBlank
    private String path;
    @NotBlank
    private String type;
    @NotNull @Min(0)
    private int seq;

    protected ProductImageUpdateRequest() {}

    public ProductImageUpdateRequest(String path,  String type, int seq) {
        this.path = path;
        this.type = type;
        this.seq = seq;
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

    public ProductImage toEntity(Product product) {
        ProductImage productImage = new ProductImage(path, type, seq);
        productImage.setProduct(product);
        return productImage;
    }
}
