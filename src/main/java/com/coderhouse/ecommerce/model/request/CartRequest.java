package com.coderhouse.ecommerce.model.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartRequest {

    @NotBlank(message = "email can't be blank")
    @Email(message = "email address is invalid")
    private String email;
    @NotBlank(message = "shipping address can't be blank")
    private String shippingAddress;
}
