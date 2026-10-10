package com.library.service.mapper;

import com.library.service.dto.request.AuthorRequest;
import com.library.service.dto.response.AuthorResponse;
import com.library.service.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public Author toEntity(AuthorRequest authorRequest){
        if(authorRequest == null){
            return null;
        }
        return Author.builder()
                .name(authorRequest.getName())
                .country(authorRequest.getCountry())
                .build();
    }

    public AuthorResponse toResponse(Author author){
        if(author == null){
            return null;
        }
        return AuthorResponse.builder()
                .id(author.getId())
                .name(author.getName())
                .country(author.getCountry())
                .booksCount(author.getBooks() == null ? 0 : author.getBooks().size())
                .build();
    }

    public void updateEntity(Author author, AuthorRequest authorRequest){
        author.setName(authorRequest.getName());
        author.setCountry(authorRequest.getCountry());
    }

}