package com.kardex.infrastructure.exceptionHandler;

import com.kardex.domain.utils.Constants;
import lombok.Getter;

@Getter
public enum ExceptionResponse {
    PRODUCT_NOT_FOUND("Product not found"),
    PROVIDER_NOT_FOUND("Provider not found"),
    PRODUCT_ALREADY_EXISTS("Product already exists"),
    PRODUCT_NOT_UPDATE("Product not updated"),
    NOT_DATA_FOUND("No data found"),
    NAME_NULL("Name must not be null"),
    DESCRIPTION_NULL("Description must not be null"),
    IMAGE_NULL("Image must not be null"),
    PRICE_NULL("Price must not be null"),
    STATUS_NULL("Status must not be null"),
    EMAIL_NULL("Email must not be null"),
    COMPANY_NULL("Company must not be null"),
    PHONE_NULL("Phone must not be null"),
    INVALID_EMAIL("The email format is invalid"),
    PHONE_MAX_LENGHT("Phone must be " + Constants.MAX_LENGHT_PHONE + " characters or less"),
    PAGE_INVALID("Page index must not be less than zero"),
    ERROR_KEY("errors"),
    MISSING_PARAMETER("The required parameter '%s' is missing."),
    MAX_UPLOAD_SIZE("The file is too large. The maximum size allowed is '%s'."),
    EXPIRED_TIME("Your session has expired. Please log in again."),
    USER_ID_NULL("User id must not be null"),
    USER_FORBIDDEN("The user does not have permission to perform this action"),
    FEIGN_UNAUTHORIZED("Not authenticated"),
    FEIGN_FORBIDDEN("Not authorized"),
    FEIGN_INTERNAL_SERVER_ERROR("Internal server error"),
    NOTIFICATION_NOT_NULL("Product name or Provider name must not be null"),
    UPLOAD_IMAGE_EXCEPTION("Error uploading image"),
    DELETE_IMAGE_EXCEPTION("Error deleting image"),
    URL_INVALID("url is invalid");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }
}
