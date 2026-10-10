package com.library.service.mapper;

import com.library.service.dto.request.BookRequest;
import com.library.service.dto.response.BookResponse;
import com.library.service.entity.Author;
import com.library.service.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book toEntity(BookRequest bookRequest, Author author){
        if (bookRequest == null){
            return null;
        }
        return Book.builder()
                .title(bookRequest.getTitle())
                .publishedYear(bookRequest.getPublishedYear())
                .author(author)
                .build();
    }

    public BookResponse toResponse(Book book){
        if (book == null){
            return null;
        }
        Author author = book.getAuthor();
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .publishedYear(book.getPublishedYear())
                .authorId(author.getId())
                .authorName(author.getName())
                .build();
    }

    public void updateEntity(Book book, BookRequest bookRequest, Author author){
        book.setTitle(bookRequest.getTitle());
        book.setPublishedYear(bookRequest.getPublishedYear());
        book.setAuthor(author);
    }
}