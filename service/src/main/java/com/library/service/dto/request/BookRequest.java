package com.library.service.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {

    @NotBlank(message = "Can´t be blank!")
    @Size(min = 2, max = 50, message = "Incorrect size!")
    private String title;

    @Min(value = 0, message = "Must be positive!")
    @Max(value = 2027, message = "must be valid!")
    private Integer publishedYear;

    @NotNull(message = "Can´t be null!")
    private Long authorId;
}