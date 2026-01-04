package com.jguarnizo.bibliotecarest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jguarnizo.bibliotecarest.model.Book;
import com.jguarnizo.bibliotecarest.model.dtos.ApiError;
import com.jguarnizo.bibliotecarest.model.dtos.CreateBookRequest;
import com.jguarnizo.bibliotecarest.model.dtos.ValidationErrorResponse;
import com.jguarnizo.bibliotecarest.services.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de libros retornado")
    })
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @Operation(summary = "Obtener un libro por su Id", description = "Encargado de bucar un libro por su Id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Libro Encontrado con exito"),
        @ApiResponse(responseCode = "404", description = "Libro no encontrado", content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    @GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Book getBookById(@Parameter(description = "Id del libro a buscar")@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @Operation(summary = "Obtener Libros por texto ingresado", description = "Encargado de obtener una lista de libros filtrando por el titulo y el nombre del autor")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de libros retornada, no importa si se retorna vacia")
    })
    @GetMapping(path = "/search", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<Book> getBooksThanText(@Parameter(description = "Texto para hacer filtrado de libros")@RequestParam(required = true) String searchText) {
        return bookService.getBooksThanText(searchText);
    }

    @Operation(summary = "Creacion de Un libro nuevo", description = "encargado de agregar un nuevo libro a la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Libro creado con exito"),
        @ApiResponse(responseCode = "400", description = "Errores de validaciones del modelo enviado", content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
        @ApiResponse(responseCode = "400", description = "El isbn del libro ingresado ya existe en el sistema", content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public Book createBook(@Valid @RequestBody(required = true) CreateBookRequest bookRequest) {
        Book book = new Book(null, bookRequest.getTitle(), bookRequest.getAuthor(), bookRequest.getIsbn(),
                bookRequest.getPublicationYear(), bookRequest.getGenre(), bookRequest.getEstate());
        return bookService.createBook(book);
    }

    @Operation(summary = "Actualizacion de un libro", description = "Encargado de actualizar un libro buscandolo por su Id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Libro actualizado con exito"),
        @ApiResponse(responseCode = "400", description = "Errores de validaciones del modelo enviado", content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "Libro no encontrado", content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    @PutMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Book updateBook(@Parameter(description = "Id del libro a actualizar")@PathVariable Long id, @Valid @RequestBody CreateBookRequest bookRequest) {
        Book book = new Book(null, bookRequest.getTitle(), bookRequest.getAuthor(), bookRequest.getIsbn(),
                bookRequest.getPublicationYear(), bookRequest.getGenre(), bookRequest.getEstate());

        return bookService.updateBook(id, book);
    }

    @Operation(summary = "Prestar un libro", description = "Encargado de marcar un libro como prestado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Libro marcado como prestado"),
        @ApiResponse(responseCode = "404", description = "Libro no encontrado", content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    @PostMapping("/{id}/lend")
    public ResponseEntity<Void> lendBook(@Parameter(description = "Id del libro a prestar")@PathVariable Long id) {
        bookService.lendBook(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Eliminar un libro", description = "Encargado de eliminar un libro por su id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Libro eliminado con exito"),
        @ApiResponse(responseCode = "404", description = "Libro no encontrado", content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@Parameter(description = "Id del libro a eliminar") @PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
