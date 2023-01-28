package connectly.assignment.product.domain;

import connectly.assignment.common.BaseEntity;
import connectly.assignment.product.dto.ProductImageRequest;
import connectly.assignment.product.dto.ProductUpdateRequest;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        // Todo 수정 시에 id 기준으로 뺴고 추가하고 유지가 필요해서 추후 개발.
//        this.productImages = builder.productImages
//                .stream()
//                .map(request -> new ProductImage(request.getPath(), request.getType(), this))
//                .collect(Collectors.toList());
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

        public Product build() {
            return new Product(this);
        }
    }
}
