package connectly.assignment.product.dto;

import connectly.assignment.product.domain.Product;

import java.util.List;

public class ProductRequest {
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
    private List<String> productImages;

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

    public List<String> getProductImages() {
        return productImages;
    }
}
