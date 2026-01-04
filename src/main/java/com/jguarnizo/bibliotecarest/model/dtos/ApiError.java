package com.jguarnizo.bibliotecarest.model.dtos;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta de Error de Negocio")
public class ApiError {
    @Schema(description = "Codigo Http", example = "404")
    private int status;
    @Schema(description = "Mensaje de error de negocio", example = "El libro no ha sido encontrado")
    private String message;
    @Schema(description = "Uri del endpoint donde ocurrio el error", example = "/api/book/2")
    private String path;
    @Schema(description = "Fecha y Hora actual en que ocurrio el evento", example = "2026-01-04T15:30:45")
    private LocalDateTime timestamp;

    public ApiError(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
