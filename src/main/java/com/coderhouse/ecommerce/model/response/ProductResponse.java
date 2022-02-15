package com.coderhouse.ecommerce.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {

    private String code;
    private String name;
    private String categoryCode;
    private String description;
    private Double price;
    private LocalDateTime modificationDate;
}
