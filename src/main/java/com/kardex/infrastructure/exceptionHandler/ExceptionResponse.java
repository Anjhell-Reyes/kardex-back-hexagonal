package com.kardex.infrastructure.exceptionHandler;

public enum ExceptionResponse {
    PRODUCT_NOT_FOUND("Product not found"),
    PRODUCT_ALREADY_EXISTS("Product already exists"),
    NOT_DATA_FOUND("No data found"),
    NAME_NULL("Name must not be null"),
    DESCRIPTION_NULL("Description must not be null"),
    IMAGE_NULL("Image must not be null"),
    PRICE_NULL("Price must not be null"),
    STATUS_NULL("Status must not be null"),
    PAGE_INVALID("Page index must not be less than zero"),
    ERROR_KEY("errors"),
    MISSING_PARAMETER("The required parameter '%s' is missing."),
    MAX_UPLOAD_SIZE("The file is too large. The maximum size allowed is '%s'."),
    EXPIRED_TIME("Your session has expired. Please log in again.");

    private String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
