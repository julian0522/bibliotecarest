package com.jguarnizo.bibliotecarest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jguarnizo.bibliotecarest.model.Book;
import com.jguarnizo.bibliotecarest.model.dtos.CreateBookRequest;
import com.jguarnizo.bibliotecarest.services.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@Tag(name = "Libros", description = "Controlador encargado de gestionar la infomacion de los libros en la API")
@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Obtener todos los libros", description = "Obtiene la lista de todos los libros existentes")
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @Operation(summary = "Obtener un libro por su Id", description = "Encargado de bucar un libro por su Id")
    @GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Book getBookById(@Parameter(description = "Id del libro a buscar")@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @Operation(summary = "Obtener Libros por texto ingresado", description = "Encargado de obtener una lista de libros filtrando por el titulo y el nombre del autor")
    @GetMapping(path = "/search", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<Book> getBooksThanText(@Parameter(description = "Texto para hacer filtrado de libros")@RequestParam(required = true) String searchText) {
        return bookService.getBooksThanText(searchText);
    }

    @Operation(summary = "Creacion de Un libro nuevo", description = "encargado de agregar un nuevo libro a la base de datos")
    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public Book createBook(@Valid @RequestBody(required = true) CreateBookRequest bookRequest) {
        Book book = new Book(null, bookRequest.getTitle(), bookRequest.getAuthor(), bookRequest.getIsbn(),
                bookRequest.getPublicationYear(), bookRequest.getGenre(), bookRequest.getEstate());
        return bookService.createBook(book);
    }

    @Operation(summary = "Actualizacion de un libro", description = "Encargado de actualizar un libro buscandolo por su Id")
    @PutMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Book updateBook(@Parameter(description = "Id del libro a actualizar")@PathVariable Long id, @Valid @RequestBody CreateBookRequest bookRequest) {
        Book book = new Book(null, bookRequest.getTitle(), bookRequest.getAuthor(), bookRequest.getIsbn(),
                bookRequest.getPublicationYear(), bookRequest.getGenre(), bookRequest.getEstate());

        return bookService.updateBook(id, book);
    }

    @Operation(summary = "Prestar un libro", description = "Encargado de marcar un libro como prestado")
    @PostMapping("/{id}/lend")
    public ResponseEntity<Void> lendBook(@Parameter(description = "Id del libro a prestar")@PathVariable Long id) {
        bookService.lendBook(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Eliminar un libro", description = "Encargado de eliminar un libro por su id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@Parameter(description = "Id del libro a eliminar") @PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
