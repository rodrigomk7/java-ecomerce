package com.coderhouse.ecommerce.model.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("product")
public class ProductDocument {

    @Id
    private String id;
    private String code;
    private String name;
    private String description;
    private Double price;
    private String categoryCode;
    private LocalDateTime modificationDate;

}
