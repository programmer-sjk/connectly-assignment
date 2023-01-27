package connectly.assignment.product.dto;

import connectly.assignment.product.domain.Product;

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
    }
}
