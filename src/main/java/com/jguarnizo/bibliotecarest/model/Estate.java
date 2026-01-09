package com.jguarnizo.bibliotecarest.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representa los diferentes estados que puede tener un libro")
public enum Estate {
    DISPONIBLE,
    PRESTADO
}
