package com.product.product.domain;

import com.product.common.BaseEntity;
import com.product.review.domain.Review;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product extends BaseEntity {
    @Id @GeneratedValue
    private Long id;

    @Comment("상품명")
    @Column(nullable = false)
    private String name;

    @Comment("브랜드명")
    @Column(nullable = false)
    private String brand;

    @Comment("원가")
    @Column(nullable = false)
    private int originPrice;

    @Comment("할인률")
    @ColumnDefault("0")
    private int discountRate;

    @Comment("상품 시리얼 번호")
    @Column(nullable = false)
    private String serial;

    @Comment("상품 상태(신상품, 기존)")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductStatus productStatus;

    @Comment("평점")
    @Column(precision = 2, scale = 1)
    private BigDecimal star = BigDecimal.valueOf(0.0);

    @Comment("원산지")
    @Column(nullable = false)
    private String madeIn;

    @Comment("출고지")
    @Column(nullable = false)
    private String shippingBy;

    @Comment("검색 시 노출 flag")
    @ColumnDefault("true")
    private boolean display;

    @Comment("상품 상세 설명")
    @Lob
    private String detail;

    @Comment("Main, Detail에 나오는 사진")
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> productImages = new ArrayList<>();

    @Comment("상품에 해당하는 리뷰")
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    protected Product() {}

    public Product(Builder builder) {
        this.name = builder.name;
        this.brand = builder.brand;
        this.originPrice = builder.originPrice;
        this.serial = builder.serial;
        this.productStatus = ProductStatus.valueOf(builder.productStatus);
        this.madeIn = builder.madeIn;
        this.shippingBy = builder.shippingBy;
        this.display = builder.display;
        this.detail = builder.detail;

        for (ProductImage image : builder.productImages) {
            addProductImage(image);
        }
    }

    public void update(Product product) {
        this.name = product.name;
        this.brand = product.brand;
        this.originPrice = product.originPrice;
        this.discountRate = product.discountRate;
        this.productStatus = product.productStatus;
        this.madeIn = product.madeIn;
        this.shippingBy = product.shippingBy;
        this.display = product.display;
    }

    public void updateDetail(String detail) {
        this.detail = detail;
    }

    public void addProductImage(ProductImage productImage) {
        this.productImages.add(productImage);
        productImage.setProduct(this);
    }

    public void addReview(Review review) {
        this.reviews.add(review);
        review.setProduct(this);
    }

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

    public ProductStatus getProductStatus() {
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

    public boolean isDisplay() {
        return display;
    }

    public String getDetail() {
        return detail;
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<Review> getReviews() {
        return reviews;
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
        private List<ProductImage> productImages = new ArrayList<>();

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

        public Builder productImages(List<ProductImage> productImages) {
            this.productImages = productImages;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
