package com.jguarnizo.bibliotecarest.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Book", description = "Modelo que representa un libro")
public class Book {
    
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private Integer publicationYear;
    private String genre;
    private Estate estate;

    public Book() {
    }

    public Book(Long id, String title, String author, String isbn, Integer publicationYear, String genre,
            Estate estate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.estate = estate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Estate getEstate() {
        return estate;
    }

    public void setEstate(Estate estate) {
        this.estate = estate;
    }
}
