package com.coderhouse.ecommerce.model.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin {

    @NotBlank(message = "email can't be blank")
    @Email(message = "email address is invalid")
    private String email;
    @NotBlank(message = "password can't be blank")
    private String password;
}
