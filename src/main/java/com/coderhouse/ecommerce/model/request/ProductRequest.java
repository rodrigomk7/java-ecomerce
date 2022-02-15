package com.coderhouse.ecommerce.model.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @NotBlank(message = "code can't be blank")
    @Pattern(regexp = "^[P].*$", message = "code of product needs to start with 'P'")
    private String code;
    @NotBlank(message = "name can't be blank")
    private String name;
    private String description;
    @NotNull(message = "price can't be null") @Min(message = "min value is 0", value = 1)
    private Double price;
    @NotBlank(message = "category can't be blank")
    @Pattern(regexp = "^[C].*$", message = "code of category needs to start with 'C'")
    private String categoryCode;
}
