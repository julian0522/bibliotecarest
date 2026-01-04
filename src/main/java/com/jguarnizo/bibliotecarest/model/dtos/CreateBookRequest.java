package com.jguarnizo.bibliotecarest.model.dtos;

import com.jguarnizo.bibliotecarest.model.Estate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Representa un Libro")
public class CreateBookRequest {

    @Schema(description = "Titulo del Libro", example = "Cien Años de Soledad")
    @NotBlank
    @Size(min = 1, max = 100, message = "El campo ''")
    private String title;

    @Schema(description = "Nombre del Autor", example = "Gabriel Garcia Marquez")
    @NotBlank
    private String author;

    @Schema(description = "Identificador único de libro", example = "978-0-306-40615-7")
    @NotBlank
    private String isbn;

    @Schema(description = "Año de publicacion del Libro", example = "2025")
    @NotNull
    @Min(value = 1500, message = "El campo 'publicationYear' debe ser minimo 1500")
    @Max(value = 2026, message = "El campo 'publicationYear' no puede ser mayor al año actual")
    private Integer publicationYear;

    @Schema(description = "Genero del libro, es Opcional", example = "Terror")
    private String genre;

    @Schema(description = "Estado del Libro", example = "DISPONIBLE")
    @NotNull
    private Estate estate;

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
