package com.coderhouse.ecommerce.model.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {

    @NotBlank(message = "code can't be blank")
    @Pattern(regexp = "^[C].*$", message = "code of category needs to start with 'C'")
    private String code;
    @NotBlank(message = "name can't be blank")
    private String name;
}