package com.coderhouse.ecommerce.model.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("category")
public class CategoryDocument {

    @Id
    private String id;
    private String code;
    private String name;
    private LocalDateTime modificationDate;
}
