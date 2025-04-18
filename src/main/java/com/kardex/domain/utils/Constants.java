package com.kardex.domain.utils;

import java.util.regex.Pattern;

public class Constants {

    //Controller

    // Base paths
    public static final String PRODUCTS_BASE_PATH = "/products";
    public static final String PRODUCT_ID_PATH = "/{productId}";
    public static final String PRODUCT_QUANTITY_UPDATE = "/{productId}/quantity";
    public static final String PROVIDERS_BASE_PATH = "/providers";
    public static final String PROVIDER_ID_PATH = "/{providerId}";
    public static final String PRODUCTS_PROVIDER_PATH = "/{providerId}/provider";
    public static final String GET_PROVIDER_NAME_PATH = "/providerName";


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
    public static final String SORT_BY_PROVIDER_NAME = "provider.name";

    //Dto
    public static final String NAME_NOT_BLANK_MESSAGE = "The name cannot be blank";
    public static final String DESCRIPTION_NOT_BLANK_MESSAGE = "The description cannot be blank";
    public static final String COMPANY_NOT_BLANK_MESSAGE = "The company cannot be blank";
    public static final String EMAIL_NOT_BLANK_MESSAGE = "The email cannot be blank";
    public static final String PHONE_NOT_BLANK_MESSAGE = "The email cannot be blank";
    public static final String QUANTITY_POSITIVE_MESSAGE = "Quantity must be greater than zero";
    public static final String PRICE_NOT_NULL_MESSAGE = "The price cannot be null";
    public static final String STATUS_NOT_NULL_MESSAGE = "The status cannot be null";
    public static final String PRICE_POSITIVE_MESSAGE = "Price must be greater than zero";
    public static final String EMAIL_VALID_FORMAT_MESSAGE = "The email must be in a valid format";
    public static final String PHONE_SIZE_MESSAGE = "The phone cannot have more than 13 digits";
    public static final String PROVIDER_NOT_NULL_MESSAGE = "The provider cannot be null";

    //Azure
    public static final String AZURE_CONNECTION = "${azure.storage.connection-string}";
    public static final String AZURE_PRODUCTS_CONTAINER_NAME = "${azure.storage.products-container-name}";
    public static final String AZURE_PROVIDERS_CONTAINER_NAME = "${azure.storage.providers-container-name}";
    public static final String FILE_NAME_SEPARATOR = "_";

    //Cors
    public static final String CORS_ALLOWED_PATHS = "/**";
    public static final String CORS_ALLOWED_ORIGIN = "http://127.0.0.1:5501";
    public static final String[] CORS_ALLOWED_METHODS = { "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS" };
    public static final String ORDER_SERVICE = "4026009529";

    //use cases
    public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    public static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    public static final int MAX_LENGHT_PHONE = 13;
    public static final String SEPARATOR_1 = " - ";
    public static final Integer LOW_STOCK_THRESHOLD = 5;

    private Constants() {

    }
}
