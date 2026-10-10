package com.library.service.service.impl;

import com.library.service.dto.request.BookRequest;
import com.library.service.dto.response.BookResponse;
import com.library.service.entity.Author;
import com.library.service.entity.Book;
import com.library.service.exception.ApiException;
import com.library.service.exception.ErrorCode;
import com.library.service.mapper.BookMapper;
import com.library.service.repository.AuthorRepository;
import com.library.service.repository.BookRepository;
import com.library.service.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    @Override
    @Transactional(readOnly = true)
    public List<BookResponse> getAll() {
        return bookRepository.findAll().stream()
                .map(b -> bookMapper.toResponse(b)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public BookResponse getById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ApiException(
                        ErrorCode.BOOK_NOT_FOUND,
                        Map.of("bookId", id)
                ));
        return bookMapper.toResponse(book);
    }

    @Override
    public List<BookResponse> getByAuthor(Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ApiException(
                        ErrorCode.AUTHOR_NOT_FOUND,
                        Map.of("authorId", authorId)
                ));
        return bookRepository.findAllByAuthorId(authorId).stream()
                .map(b -> bookMapper.toResponse(b)).toList();
    }

    @Override
    public BookResponse create(BookRequest request) {
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new ApiException(
                        ErrorCode.AUTHOR_NOT_FOUND,
                        Map.of("authorId", request.getAuthorId())
                ));
        Book book = bookRepository.save(bookMapper.toEntity(request, author));
        return bookMapper.toResponse(book);
    }

    @Override
    public BookResponse update(Long id, BookRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ApiException(
                        ErrorCode.BOOK_NOT_FOUND,
                        Map.of("bookId", id)
                ));
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new ApiException(
                        ErrorCode.AUTHOR_NOT_FOUND,
                        Map.of("authorId", request.getAuthorId())
                ));
        bookMapper.updateEntity(book, request, author);
        return bookMapper.toResponse(bookRepository.save(book));
    }

    @Override
    public void delete(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ApiException(
                        ErrorCode.BOOK_NOT_FOUND,
                        Map.of("bookId", id)
                ));
        bookRepository.delete(book);
    }

}