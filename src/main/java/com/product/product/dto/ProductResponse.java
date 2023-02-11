package com.product.product.dto;

import com.product.product.domain.Product;
import com.product.review.dto.ReviewResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ProductResponse {
    private Long id;
    private String name;
    private String brand;
    private int originPrice;
    private int discountRate;
    private String serial;
    private String productStatus;
    private BigDecimal star;
    private String madeIn;
    private String shippingBy;
    private String detail;
    private List<ProductImageResponse> productImages;
    private List<ReviewResponse> reviews;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.brand = product.getBrand();
        this.originPrice = product.getOriginPrice();
        this.discountRate = product.getDiscountRate();
        this.serial = product.getSerial();
        this.productStatus = product.getProductStatus().name();
        this.star = product.getStar();
        this.madeIn = product.getMadeIn();
        this.shippingBy = product.getShippingBy();
        this.detail = product.getDetail();
        this.productImages = product.getProductImages()
                .stream()
                .map(ProductImageResponse::new)
                .collect(Collectors.toList());
        this.reviews = product.getReviews()
                .stream()
                .map(ReviewResponse::new)
                .collect(Collectors.toList());
    }

    protected ProductResponse() {}

    public Long getId() {
        return id;
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

    public BigDecimal getStar() {
        return star;
    }

    public String getMadeIn() {
        return madeIn;
    }

    public String getShippingBy() {
        return shippingBy;
    }

    public String getDetail() {
        return detail;
    }

    public List<ProductImageResponse> getProductImages() {
        return productImages;
    }

    public List<ReviewResponse> getReviews() {
        return reviews;
    }
}
