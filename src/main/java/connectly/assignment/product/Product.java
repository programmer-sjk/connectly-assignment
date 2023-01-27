package connectly.assignment.product;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id @GeneratedValue
    private Long id;

    @Comment("상품명")
    private String name;

    @Comment("브랜드명")
    private String brand;

    @Comment("원가")
    private int originPrice;

    @Comment("할인률")
    private int discountRate;

    @Comment("상품 시리얼 번호")
    private String serial;

    @Comment("상품 상태(신상품, 기존)")
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @Comment("평점")
    @Column(precision = 2, scale = 1, columnDefinition = "default 0.0")
    private BigDecimal star;

    @Comment("원산지")
    private String madeIn;

    @Comment("출고지")
    private String shippingBy;

    @Comment("검색 시 노출 flag")
    private boolean display;

    @Comment("상품 상세 설명")
    @Lob
    private String detail;

    @Comment("Main, Detail에 나오는 사진")
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> productImages = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
