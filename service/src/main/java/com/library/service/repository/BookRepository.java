package com.library.service.repository;

import com.library.service.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByAuthorId(Long authorId);

    //boolean existsByBookId(Long id);
}