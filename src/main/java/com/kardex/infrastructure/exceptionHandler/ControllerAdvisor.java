package com.kardex.infrastructure.exceptionHandler;

import com.kardex.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "Message";

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleProductAlreadyExistsException(
            ProductAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.PRODUCT_ALREADY_EXISTS.getMessage()));
    }

    @ExceptionHandler(NotDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotDataFoundException(
            NotDataFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NOT_DATA_FOUND.getMessage()));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleProductNotFoundException(
            ProductNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.PRODUCT_NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<Map<String, String>> handleMissingServletRequestPartException(
            MissingServletRequestPartException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE,
                        String.format(ExceptionResponse.MISSING_PARAMETER.getMessage(), exception.getRequestPartName())));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Map<String, String>> handleMaxUploadSizeExceededException(
            MaxUploadSizeExceededException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE,
                        String.format(ExceptionResponse.MAX_UPLOAD_SIZE.getMessage(), exception.getMaxUploadSize())));
    }

    @ExceptionHandler(NameNotNullException.class)
    public ResponseEntity<Map<String, String>> handleNameNotNullException(
            NameNotNullException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NAME_NULL.getMessage()));
    }

    @ExceptionHandler(ImageNotNullException.class)
    public ResponseEntity<Map<String, String>> handleImageNotNullException(
            ImageNotNullException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.IMAGE_NULL.getMessage()));
    }

    @ExceptionHandler(DescriptionNotNullException.class)
    public ResponseEntity<Map<String, String>> handleDescriptionNotNullException(
            DescriptionNotNullException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.DESCRIPTION_NULL.getMessage()));
    }

    @ExceptionHandler(PriceNotNullException.class)
    public ResponseEntity<Map<String, String>> handlePriceNotNullException(
            PriceNotNullException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.PRICE_NULL.getMessage()));
    }

    @ExceptionHandler(StatusNotNullException.class)
    public ResponseEntity<Map<String, String>> handleStatusNotNullException(
            StatusNotNullException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.STATUS_NULL.getMessage()));
    }

    @ExceptionHandler(InvalidPageIndexException.class)
    public ResponseEntity<Map<String, String>> handleInvalidPageIndexException(
            InvalidPageIndexException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.PAGE_INVALID.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Map<String, String>>>  handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errorMessages = new HashMap<>();

        // Recorrer todos los errores de validación
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errorMessages.put(error.getField(), error.getDefaultMessage());
        }

        // Crear el objeto final con la clave "errors"
        Map<String, Map<String, String>> response = new HashMap<>();
        response.put(ExceptionResponse.ERROR_KEY.getMessage(), errorMessages);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
