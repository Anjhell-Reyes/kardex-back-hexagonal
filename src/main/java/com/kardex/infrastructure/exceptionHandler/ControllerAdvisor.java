package com.kardex.infrastructure.exceptionHandler;

import com.kardex.domain.exception.*;
import io.jsonwebtoken.ExpiredJwtException;
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
    private static final String AVAILABLE_PROVIDERS = "availableProviders";

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleProductAlreadyExistsException(
            ProductAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.PRODUCT_ALREADY_EXISTS.getMessage()));
    }

    @ExceptionHandler(URLCloudinayInvalidException.class)
    public ResponseEntity<Map<String, String>> handleURLCloudinayInvalidException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.URL_INVALID.getMessage()));
    }

    @ExceptionHandler(UploadImageCloudinaryException.class)
    public ResponseEntity<Map<String, String>> handleUploadImageCloudinaryException() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.UPLOAD_IMAGE_EXCEPTION.getMessage()));
    }

    @ExceptionHandler(DeleteImageClodinaryException.class)
    public ResponseEntity<Map<String, String>> handleDeleteImageClodinaryException() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.DELETE_IMAGE_EXCEPTION.getMessage()));
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

    @ExceptionHandler(ProviderNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleProviderNotFoundException(
            ProviderNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.PROVIDER_NOT_FOUND.getMessage()));
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

    @ExceptionHandler(UserIdNotNullException.class)
    public ResponseEntity<Map<String, String>> handleUserIdNotNullException(
            UserIdNotNullException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.USER_ID_NULL.getMessage()));
    }

    @ExceptionHandler(CompanyNotNullException.class)
    public ResponseEntity<Map<String, String>> handleCompanyNotNullException(
            CompanyNotNullException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.COMPANY_NULL.getMessage()));
    }

    @ExceptionHandler(EmailNotNullException.class)
    public ResponseEntity<Map<String, String>> handleEmailNotNullException(
            EmailNotNullException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.EMAIL_NULL.getMessage()));
    }

    @ExceptionHandler(PhoneNotNullException.class)
    public ResponseEntity<Map<String, String>> handlePhoneNotNullException(
            PhoneNotNullException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.PHONE_NULL.getMessage()));
    }

    @ExceptionHandler(AvailableProvidersException.class)
    public ResponseEntity<Map<String, Object>> handleAvailableProvidersException(
            AvailableProvidersException exception) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put(MESSAGE, ExceptionResponse.PROVIDER_NOT_FOUND.getMessage());
        responseBody.put(AVAILABLE_PROVIDERS, exception.getAvailableProviders());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }

    @ExceptionHandler(InvalidEmailFormatException.class)
    public ResponseEntity<Map<String, String>> handleInvalidEmailFormatException(
            InvalidEmailFormatException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.INVALID_EMAIL.getMessage()));
    }

    @ExceptionHandler(PhoneMaxCharactersException.class)
    public ResponseEntity<Map<String, String>> handlePhoneMaxCharactersException(
            PhoneMaxCharactersException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.PHONE_MAX_LENGHT.getMessage()));
    }

    @ExceptionHandler(InvalidPageIndexException.class)
    public ResponseEntity<Map<String, String>> handleInvalidPageIndexException(
            InvalidPageIndexException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.PAGE_INVALID.getMessage()));
    }
    
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Map<String, String>> handleExpiredJwtException(
            ExpiredJwtException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.EXPIRED_TIME.getMessage()));
    }

    @ExceptionHandler(UserForbiddenException.class)
    public ResponseEntity<Map<String, String>> handleUserForbiddenException(
            UserForbiddenException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.USER_FORBIDDEN.getMessage()));
    }

    @ExceptionHandler(ProductNotUpdatedException.class)
    public ResponseEntity<Map<String, String>> handleProductNotUpdatedException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.PRODUCT_NOT_UPDATE.getMessage()));
    }


    @ExceptionHandler(FeignServerErrorException.class)
    public ResponseEntity<Map<String, String>> handleFeignServerErrorException() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.FEIGN_INTERNAL_SERVER_ERROR.getMessage()));
    }


    @ExceptionHandler(FeignForbiddenException.class)
    public ResponseEntity<Map<String, String>> handleFeignForbiddenException() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.FEIGN_FORBIDDEN.getMessage()));
    }

    @ExceptionHandler(FeignUnauthorizedException.class)
    public ResponseEntity<Map<String, String>> handleFeignUnAuthorizedException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.FEIGN_UNAUTHORIZED.getMessage()));
    }

    @ExceptionHandler(NotificationNotNullxception.class)
    public ResponseEntity<Map<String, String>> handleNotificationNotNullxception() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NOTIFICATION_NOT_NULL.getMessage()));
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
