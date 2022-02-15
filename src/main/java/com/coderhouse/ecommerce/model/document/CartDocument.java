package com.coderhouse.ecommerce.model.document;

import com.coderhouse.ecommerce.model.request.ItemRequest;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("cart")
public class CartDocument {

    @Id
    private String id;
    private String email;
    @Singular
    private List<ItemRequest> products;
    private String shippingAddress;
    private LocalDateTime modificationDate;

}
