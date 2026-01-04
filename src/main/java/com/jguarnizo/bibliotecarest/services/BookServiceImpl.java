package com.jguarnizo.bibliotecarest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.jguarnizo.bibliotecarest.exceptions.BookExistException;
import com.jguarnizo.bibliotecarest.exceptions.BookNotFoundException;
import com.jguarnizo.bibliotecarest.model.Book;
import com.jguarnizo.bibliotecarest.model.Estate;

@Service
public class BookServiceImpl implements BookService {

    private final AtomicLong consecutive;
    private final List<Book> books;

    public BookServiceImpl() {
        this.books = new ArrayList<>();
        this.consecutive = new AtomicLong(1);
    }

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<Book>(books);
    }

    @Override
    public Book getBookById(Long id) {
        return books.stream().filter(b -> b.getId().equals(id)).findFirst()
                .orElseThrow(() -> new BookNotFoundException("El libro con id: " + id + " no ha sido encontrado"));
    }

    @Override
    public List<Book> getBooksThanText(String searchText) {
        if (searchText == null || searchText.isBlank()) return getAllBooks();

        return books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(searchText.toLowerCase()) || b.getAuthor().toLowerCase().contains(searchText.toLowerCase()))
                .toList();
    }

    @Override
    public Book createBook(Book book) {
        var existBook = books.stream().filter(b -> b.getIsbn().equalsIgnoreCase(book.getIsbn())).findFirst();
        if (existBook.isPresent()){
            throw new BookExistException("El Libro con Isbn: " + book.getIsbn() + " ya existe en base de datos");
        }

        book.setId(consecutive.getAndIncrement());
        books.add(book);
        return book;
    }

    @Override
    public Book updateBook(Long id, Book book) {
        var updateBook = getBookById(id);

        updateBook.setTitle(book.getTitle());
        updateBook.setAuthor(book.getAuthor());
        updateBook.setIsbn(book.getIsbn());
        updateBook.setGenre(book.getGenre() != null ? book.getGenre() : null);
        updateBook.setPublicationYear(book.getPublicationYear());
        updateBook.setEstate(book.getEstate());
        return updateBook;
    }

    @Override
    public void deleteBook(Long id) {
        var book = getBookById(id);
        books.remove(book);
    }

    @Override
    public void lendBook(Long id) {
        var book = getBookById(id);
        book.setEstate(Estate.PRESTADO);
    }

}
