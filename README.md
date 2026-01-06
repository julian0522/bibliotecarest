Perfecto 👍
Te dejo un **README profesional**, bien estructurado, con tono académico/profesional, ideal para:

* entregar en un curso
* subir a GitHub
* mostrar como proyecto integrador

Lo redacto en **Markdown**, listo para copiar y pegar en `README.md`.

---

# 📚 Sistema Simplificado de Gestión de Biblioteca

**API RESTful en Java con Spring Boot**

## 🧾 Descripción General

Este proyecto consiste en una **API RESTful** desarrollada en **Java con Spring Boot**, cuyo objetivo es gestionar de manera básica la información de una biblioteca. La aplicación implementa una arquitectura limpia por capas, buenas prácticas de desarrollo y herramientas profesionales ampliamente utilizadas en proyectos reales.

La persistencia de datos se realiza **en memoria**, utilizando la estructura de `List`, simulando el comportamiento de una base de datos. Por este motivo, **la información se pierde al reiniciar la aplicación**, lo cual es intencional para fines académicos y de aprendizaje.

---

## 🎯 Objetivo del Proyecto

Construir una API REST completa y bien estructurada que permita:

* Gestionar libros mediante operaciones CRUD
* Aplicar **validaciones de datos** usando anotaciones de Jakarta Validation
* Implementar **manejo global de excepciones**
* Incorporar **logging profesional** con SLF4J y Log4j2
* Documentar la API de forma automática usando **Swagger / OpenAPI**
* Aplicar principios de **arquitectura por capas** y **buenas prácticas**
* Aplicar principios SOLID

---

## 🏗️ Arquitectura del Proyecto

El proyecto sigue una **arquitectura por capas**, separando claramente responsabilidades:

* **Controller**: expone los endpoints REST
* **Service**: contiene la lógica de negocio
* **Model / DTO**: representa las entidades y objetos de transferencia
* **Exception**: manejo centralizado de errores
* **Configurations**: configuración de logging y documentación

Esta estructura facilita la mantenibilidad, escalabilidad y testeo de la aplicación.

---

## ⚙️ Tecnologías Utilizadas

* **Java**
* **Spring Boot 4**
* **Spring Web (REST)**
* **SLF4J + Log4j2** (logging profesional)
* **Jakarta Validation** (validación por anotaciones)
* **SpringDoc OpenAPI** (Swagger)
* **Maven** (gestión de dependencias)

---

## 📖 Documentación de la API (Swagger)

La API está documentada automáticamente usando **SpringDoc OpenAPI**.

Una vez iniciada la aplicación, la documentación interactiva se encuentra disponible en:

```
http://localhost:8080/swagger-ui/index.html
```

Desde allí es posible:

* Visualizar todos los endpoints
* Probar solicitudes HTTP
* Consultar modelos y validaciones

---

## 📌 Funcionalidades Principales

### 📕 Gestión de Libros

La entidad principal del sistema es **Libro**, que contiene los siguientes atributos:

* `id` → Identificador único (generado en memoria)
* `titulo` → Título del libro
* `autor` → Autor
* `isbn` → Código ISBN único
* `anioPublicacion` → Año de publicación
* `genero` → Género (opcional)
* `estado` → Disponible / Prestado

---

### 🔗 Endpoints Disponibles

| Método | Endpoint                   | Descripción                |
| ------ | -------------------------- | -------------------------- |
| POST   | `/api/book`              | Crear un nuevo libro       |
| GET    | `/api/book`              | Obtener todos los libros   |
| GET    | `/api/book/{id}`         | Obtener un libro por ID    |
| PUT    | `/api/book/{id}`         | Actualizar un libro        |
| DELETE | `/api/book/{id}`         | Eliminar un libro          |
| GET    | `/api/book/search?searchText=`    | Buscar por título o autor  |
| POST   | `/api/book/{id}/lend` | Marcar libro como prestado |

---

## ✅ Validaciones de Datos

Se implementan validaciones mediante **Jakarta Validation**, utilizando anotaciones como:

* `@NotBlank`
* `@NotNull`
* `@Size`
* `@Min`

Estas validaciones garantizan la integridad de los datos antes de ser procesados por la lógica de negocio.

Los errores de validación son capturados y devueltos de forma clara al cliente.

---

## 🚨 Manejo Global de Excepciones

La aplicación cuenta con un **GlobalExceptionHandler**, el cual:

* Centraliza el manejo de errores
* Devuelve respuestas HTTP consistentes
* Diferencia entre:

  * errores de validación (400)
  * reglas de negocio (404)
  * errores inesperados del sistema (500)

Esto mejora la experiencia del consumidor de la API y mantiene el código limpio.

---

## 📝 Manejo de Logs

El sistema implementa logging profesional usando:

* **SLF4J** como fachada
* **Log4j2** como implementación

Buenas prácticas aplicadas:

* Registro de excepciones con stacktrace cuando corresponde
* Logs claros y contextualizados (endpoint, mensaje, causa)

El logging permite:

* Facilitar el diagnóstico de errores
* Auditar el comportamiento de la aplicación
* Simular escenarios reales de producción

---

## 👨‍💻 Autor

Proyecto desarrollado por **Julian Eduardo Guarnizo**
Curso: Java Backend / Spring Boot
Módulo 5: Proyecto Integrador
