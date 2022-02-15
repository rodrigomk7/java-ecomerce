package com.coderhouse.ecommerce.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemResponse {

    private String productName;
    private String productDescription;
    private Double productPrice;
    private String categoryName;
    private Integer quantity;

    @Override
    public String toString() {
        return "(" + quantity + ")" + "x. " + productName + " - $" + productPrice*quantity;
    }
}
