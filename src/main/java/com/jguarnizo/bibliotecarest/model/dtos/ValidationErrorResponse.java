package com.jguarnizo.bibliotecarest.model.dtos;

import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta de Error de Validacion")
public class ValidationErrorResponse {
    
    @Schema(description = "Codigo Http", example = "400")
    private int status;
    @Schema(description = "Mensaje de error", example = "Error de validacion")
    private String message;
    @Schema(description = "Uri del endpoint donde ocurrio el error", example = "/api/book")
    private String path;
    @Schema(description = "Errores por campo", example = "{\"title\":\"El titulo es obligatorio\",\"isbn\":\"ISBN inválido\"}")
    private Map<String, String> errors;

    public ValidationErrorResponse(
            int status,
            String message,
            String path,
            Map<String, String> errors) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.errors = errors;
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

    public Map<String, String> getErrors() {
        return errors;
    }
}
