package com.library.service.controller;

import com.library.service.dto.request.AuthorRequest;
import com.library.service.dto.request.BookRequest;
import com.library.service.dto.response.AuthorResponse;
import com.library.service.dto.response.BookResponse;
import com.library.service.service.AuthorService;
import com.library.service.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <h2>REST API контроллер</h2>
 * <p>Базовый путь: <code>/api</code></p>
 *
 * <h3>Список реализуемых эндпоинтов:</h3>
 *
 * <h4>Авторы (Authors)</h4>
 * <table border="1">
 *   <tr>
 *     <th>HTTP-метод</th>
 *     <th>URL</th>
 *     <th>Описание</th>
 *   </tr>
 *   <tr>
 *     <td>POST</td>
 *     <td><code>/api/authors</code></td>
 *     <td>Создать нового автора</td>
 *   </tr>
 *   <tr>
 *     <td>GET</td>
 *     <td><code>/api/authors</code></td>
 *     <td>Получить список всех авторов</td>
 *   </tr>
 *   <tr>
 *     <td>GET</td>
 *     <td><code>/api/authors/{id}</code></td>
 *     <td>Получить автора по идентификатору</td>
 *   </tr>
 *   <tr>
 *     <td>PUT</td>
 *     <td><code>/api/authors/{id}</code></td>
 *     <td>Обновить данные автора по идентификатору</td>
 *   </tr>
 *   <tr>
 *     <td>DELETE</td>
 *     <td><code>/api/authors/{id}</code></td>
 *     <td>Удалить автора по идентификатору</td>
 *   </tr>
 * </table>
 *
 * <h4>Книги (Books)</h4>
 * <table border="1">
 *   <tr>
 *     <th>HTTP-метод</th>
 *     <th>URL</th>
 *     <th>Описание</th>
 *   </tr>
 *   <tr>
 *     <td>POST</td>
 *     <td><code>/api/books</code></td>
 *     <td>Создать новую книгу</td>
 *   </tr>
 *   <tr>
 *     <td>GET</td>
 *     <td><code>/api/books</code></td>
 *     <td>Получить список всех книг</td>
 *   </tr>
 *   <tr>
 *     <td>GET</td>
 *     <td><code>/api/books/{id}</code></td>
 *     <td>Получить книгу по идентификатору</td>
 *   </tr>
 *   <tr>
 *     <td>PUT</td>
 *     <td><code>/api/books/{id}</code></td>
 *     <td>Обновить данные книги по идентификатору</td>
 *   </tr>
 *   <tr>
 *     <td>DELETE</td>
 *     <td><code>/api/books/{id}</code></td>
 *     <td>Удалить книгу по идентификатору</td>
 *   </tr>
 * </table>
 *
 * @author  vlzov
 * @version 0.0.1-SNAPSHOT
 * @since   26-09-2026
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LibraryController {

    private final AuthorService authorService;
    private final BookService bookService;

    @PostMapping("/authors")
    public ResponseEntity<AuthorResponse> createAuthor(@Valid @RequestBody AuthorRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.create(request));
    }

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorResponse>> getAllAuthors(){
        return ResponseEntity.ok(authorService.getAll());
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable Long id){
        return ResponseEntity.ok(authorService.getById(id));
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<AuthorResponse> updateAuthor(@PathVariable Long id, @Valid @RequestBody AuthorRequest request){
        return ResponseEntity.ok(authorService.update(id, request));
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id){
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/books")
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(request));
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookResponse>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAll());
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id){
        return ResponseEntity.ok(bookService.getById(id));
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequest request){
        return ResponseEntity.ok(bookService.update(id, request));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}