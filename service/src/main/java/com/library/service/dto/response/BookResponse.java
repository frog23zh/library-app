package com.library.service.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse {

    private Long id;

    private String title;

    private Integer publishedYear;

    private Long authorId;

    private String authorName;

}