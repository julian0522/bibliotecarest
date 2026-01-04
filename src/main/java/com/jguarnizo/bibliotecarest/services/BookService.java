package com.jguarnizo.bibliotecarest.services;

import java.util.List;

import com.jguarnizo.bibliotecarest.exceptions.BookExistException;
import com.jguarnizo.bibliotecarest.exceptions.BookNotFoundException;
import com.jguarnizo.bibliotecarest.model.Book;

public interface BookService {

    /**
     * Metodo encargado de obtener una lista de libros
     * 
     * @return Lista de libros {@link Book}
     */
    List<Book> getAllBooks();

    /**
     * Metodo encargado de obtener un solo libro por su Id
     * 
     * @param id Identificador unico del libro
     * @return Un solo libro {@link Book}
     * @throws BookNotFoundException Si no existe ningun libro con ese Id
     */
    Book getBookById(Long id);

    /**
     * Metodo encargado de obtener una lista de libros en los cuales su titulo o autor contengan el texto ingresado
     * <p>
     * Si el texto es nulo o vacio, retorna todos los libros
     * 
     * @param searchText Texto ingresado para hacer el filtrado de libros
     * @return Lista de libros {@link Book}
     */
    List<Book> getBooksThanText(String searchText);

    /**
     * Metodo para crear un libro
     * 
     * @param book Lirbo a crear
     * @return Libro creado {@link Book}
     * @throws BookExistException Si el identificador unico del libro(ISBN) a crear ya existe en otro libro
     */
    Book createBook(Book book);

    /**
     * Metodo encargado de actualizar toda la informacion del libro
     * 
     * @param id Identificador unico del libro a actualizar
     * @param book Informacion completa del libro a actualizar
     * @return Libro que fue actualizado {@link Book}
     * @throws BookNotFoundException Si no exiete el libro con el id ingresado
     */
    Book updateBook(Long id, Book book);

    /**
     * Metodo encargado de eliminar un libro buscandolo por si Id
     * 
     * @param id Identificador unico del libro
     * @throws BookNotFoundException Si no exiete el libro con el id ingresado
     */
    void deleteBook(Long id);

    /**
     * Metodo encargado de marcar un libro como prestado, cambia el estado del libro a prestado
     * 
     * @param id Identificador unico del libro
     * @throws BookNotFoundException Si no exiete el libro con el id ingresado
     */
    void lendBook(Long id);
}
