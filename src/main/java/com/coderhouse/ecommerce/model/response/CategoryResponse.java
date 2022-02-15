package com.coderhouse.ecommerce.model.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {

    private String code;
    private String name;
    private LocalDateTime modificationDate;
}
