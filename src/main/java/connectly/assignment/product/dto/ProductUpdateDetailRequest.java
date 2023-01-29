package connectly.assignment.product.dto;

import jakarta.validation.constraints.NotBlank;

public class ProductUpdateDetailRequest {
    @NotBlank
    private String detail;

    protected ProductUpdateDetailRequest() {}

    public ProductUpdateDetailRequest(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }
}
