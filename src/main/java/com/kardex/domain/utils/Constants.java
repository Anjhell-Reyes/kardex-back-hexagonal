package com.kardex.domain.utils;

public class Constants {

    //Controller

    // Base paths
    public static final String PRODUCTS_BASE_PATH = "/products";
    public static final String PRODUCT_ID_PATH = "/{productId}";

    // HTTP Status Codes
    public static final String CREATED = "201";
    public static final String CONFLICT = "409";
    public static final String OK = "200";
    public static final String NOT_FOUND = "404";

    // Default Pagination Values
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_SIZE = "10";
    public static final String DEFAULT_SORT_BY = "id";
    public static final String DEFAULT_ASC = "true";

    //Dto
    public static final String NAME_NOT_BLANK_MESSAGE = "The name cannot be blank";
    public static final String DESCRIPTION_NOT_BLANK_MESSAGE = "The description cannot be blank";
    public static final String QUANTITY_POSITIVE_MESSAGE = "Quantity must be greater than zero";
    public static final String PRICE_NOT_NULL_MESSAGE = "The price cannot be null";
    public static final String STATUS_NOT_NULL_MESSAGE = "The status cannot be null";
    public static final String PRICE_POSITIVE_MESSAGE = "Price must be greater than zero";

    //Azure
    public static final String AZURE_CONNECTION = "${azure.storage.connection-string}";
    public static final String AZURE_CONTAINER_NAME = "${azure.storage.container-name}";
    public static final String FILE_NAME_SEPARATOR = "_";

    //Cors
    public static final String CORS_ALLOWED_PATHS = "/**";
    public static final String CORS_ALLOWED_ORIGIN = "http://127.0.0.1:5500";
    public static final String[] CORS_ALLOWED_METHODS = { "GET", "POST", "PUT", "DELETE", "OPTIONS" };

    public static final class Roles {
        public static final String USER_ROLE = "user";
        public static final String ADMIN_ROLE = "admin";
        public static final String SUPPLIER_ROLE = "supplier";
    }

    private Constants() {

    }
}
