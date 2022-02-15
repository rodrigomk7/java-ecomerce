package com.coderhouse.ecommerce.model.request;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequest {

    @NotBlank(message = "product code can't be blank")
    @Pattern(regexp = "^[P].*$", message = "code of product needs to start with 'P'")
    private String productCode;
    @NotNull(message = "quantity can't be blank")
    @Min(value = 1, message = "min value is 1") @Max(value = 100, message = "max value is 100")
    private Integer quantity;

}
