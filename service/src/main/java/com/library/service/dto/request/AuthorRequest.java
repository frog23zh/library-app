package com.library.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequest {

    @NotBlank(message = "Can´t be blank!")
    @Size(min = 2, max = 50, message = "Incorrect size!")
    private String name;

    @NotBlank(message = "Can´t be blank!")
    @Size(min = 2, max = 50, message = "Incorrect size!")
    private String country;

}