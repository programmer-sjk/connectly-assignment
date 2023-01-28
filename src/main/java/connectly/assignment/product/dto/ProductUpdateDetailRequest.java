package connectly.assignment.product.dto;

import jakarta.validation.constraints.NotBlank;

public class ProductUpdateDetailRequest {
    @NotBlank
    private String detail;

    public String getDetail() {
        return detail;
    }
}
