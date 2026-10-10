package com.library.service.service.impl;

import com.library.service.dto.request.AuthorRequest;
import com.library.service.dto.response.AuthorResponse;
import com.library.service.entity.Author;
import com.library.service.exception.ApiException;
import com.library.service.exception.ErrorCode;
import com.library.service.mapper.AuthorMapper;
import com.library.service.repository.AuthorRepository;
import com.library.service.repository.BookRepository;
import com.library.service.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.security.AuthorizationAuditListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final AuthorMapper authorMapper;

    @Override
    @Transactional(readOnly = true)
    public List<AuthorResponse> getAll() {
        return authorRepository.findAll().stream()
                .map(a -> authorMapper.toResponse(a)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorResponse getById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ApiException(
                        ErrorCode.AUTHOR_NOT_FOUND,
                        Map.of("authorId", id)
                ));
        return authorMapper.toResponse(author);
    }

    @Override
    public AuthorResponse create(AuthorRequest request) {
        if (authorRepository.existsByName(request.getName())) {
            throw new ApiException(
                    ErrorCode.AUTHOR_NAME_TAKEN,
                    Map.of("name", request.getName())
            );
        }
        Author author = authorRepository.save(authorMapper.toEntity(request));
        return authorMapper.toResponse(author);
    }

    @Override
    public AuthorResponse update(Long id, AuthorRequest request) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ApiException(
                        ErrorCode.AUTHOR_NOT_FOUND,
                        Map.of("authorId", id)
                ));
        if (authorRepository.existsByName(request.getName()) && !author.getName().equals(request.getName())){
            throw new ApiException(
                    ErrorCode.AUTHOR_NAME_TAKEN,
                    Map.of("name", request.getName())
            );
        }
        authorMapper.updateEntity(author, request);
        return authorMapper.toResponse(authorRepository.save(author));
    }

    @Override
    public void delete(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(()-> new ApiException(
                        ErrorCode.AUTHOR_NOT_FOUND,
                        Map.of("authorId", id)
                ));
        if (!bookRepository.findAllByAuthorId(id).isEmpty()){
            throw new ApiException(
                    ErrorCode.AUTHOR_HAS_BOOKS,
                    Map.of("authorId", id)
            );
        }
        authorRepository.delete(author);
    }

}