package connectly.assignment.product.dto;

import connectly.assignment.product.domain.Product;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

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
    private List<ProductImageRequest> productImages;

    public Product toEntity() {
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
                .productImages(productImages)
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
}
