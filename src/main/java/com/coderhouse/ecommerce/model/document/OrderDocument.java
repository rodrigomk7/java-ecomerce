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
@Document("order")
public class OrderDocument {

    @Id
    private String id;
    private Long orderNumber;
    private List<ItemRequest> products;
    private LocalDateTime orderDate;
    private String stateOfOrder;
    private String email;
    private String shippingAddress;
}
