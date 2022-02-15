package com.coderhouse.ecommerce.model.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("user")
public class UserDocument {

    @Id
    private String id;
    private String name;
    private String email;
    private String telephone;
    private String password;
}
