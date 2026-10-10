package com.library.service.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorResponse {

    private Long id;

    private String name;

    private String country;

    private int booksCount;
}