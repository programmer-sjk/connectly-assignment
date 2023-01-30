package connectly.assignment.product.dto;

import connectly.assignment.product.domain.Product;
import connectly.assignment.product.domain.ProductImage;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String brand;
    @Min(0)
    private int originPrice;
    @Min(0) @Max(100)
    private int discountRate;
    @NotBlank
    private String serial;
    @NotBlank
    private String productStatus;
    @NotBlank
    private String madeIn;
    @NotBlank
    private String shippingBy;
    private boolean display;
    private String detail;
    @Valid
    private List<ProductImageRequest> productImages;

    protected ProductRequest() {}

    public ProductRequest(Builder builder) {
        this.name = builder.name;
        this.brand = builder.brand;
        this.originPrice = builder.originPrice;
        this.discountRate = builder.discountRate;
        this.serial = builder.serial;
        this.productStatus = builder.productStatus;
        this.madeIn = builder.madeIn;
        this.shippingBy = builder.shippingBy;
        this.display = builder.display;
        this.detail = builder.detail;
        this.productImages = builder.productImages;
    }

    public Product toEntity() {
        List<ProductImage> images = productImages.stream()
                .map(ProductImageRequest::toEntity)
                .collect(Collectors.toList());

        return new Product.Builder()
                .name(name)
                .brand(brand)
                .originPrice(originPrice)
                .discountRate(discountRate)
                .serial(serial)
                .productStatus(productStatus)
                .madeIn(madeIn)
                .shippingBy(shippingBy)
                .display(display)
                .detail(detail)
                .productImages(images)
                .build();
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public int getOriginPrice() {
        return originPrice;
    }

    public int getDiscountRate() {
        return discountRate;
    }

    public String getSerial() {
        return serial;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public String getMadeIn() {
        return madeIn;
    }

    public String getShippingBy() {
        return shippingBy;
    }

    public boolean isDisplay() {
        return display;
    }

    public String getDetail() {
        return detail;
    }

    public List<ProductImageRequest> getProductImages() {
        return productImages;
    }

    public static class Builder {
        private String name;
        private String brand;
        private int originPrice;
        private int discountRate;
        private String serial;
        private String productStatus;
        private String madeIn;
        private String shippingBy;
        private boolean display;
        private String detail;
        private List<ProductImageRequest> productImages = new ArrayList<>();

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder originPrice(int originPrice) {
            this.originPrice = originPrice;
            return this;
        }

        public Builder discountRate(int discountRate) {
            this.discountRate = discountRate;
            return this;
        }

        public Builder serial(String serial) {
            this.serial = serial;
            return this;
        }

        public Builder productStatus(String productStatus) {
            this.productStatus = productStatus;
            return this;
        }

        public Builder madeIn(String madeIn) {
            this.madeIn = madeIn;
            return this;
        }

        public Builder shippingBy(String shippingBy) {
            this.shippingBy = shippingBy;
            return this;
        }

        public Builder display(boolean display) {
            this.display = display;
            return this;
        }

        public Builder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public Builder productImages(List<ProductImageRequest> productImages) {
            this.productImages = productImages;
            return this;
        }

        public ProductRequest build() {
            return new ProductRequest(this);
        }
    }
}
