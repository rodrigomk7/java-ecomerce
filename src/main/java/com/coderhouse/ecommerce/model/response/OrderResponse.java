package com.coderhouse.ecommerce.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponse {

    private String email;
    private Long orderNumber;
    private List<ItemResponse> products;
    private LocalDateTime orderDate;
    private String stateOfOrder;
    private String shippingAddress;
}
