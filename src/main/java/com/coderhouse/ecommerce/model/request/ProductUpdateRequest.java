package com.coderhouse.ecommerce.model.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateRequest {

    @NotBlank(message = "code can't be blank")
    @Pattern(regexp = "^[P].*$", message = "code of product needs to start with 'P'")
    private String code;

    //opcionales
    private String name;
    private String description;
    @Min(message = "min value is 0", value = 1)
    private Double price;
    @Pattern(regexp = "^[C].*$", message = "code of category needs to start with 'C'")
    private String categoryCode;
}
