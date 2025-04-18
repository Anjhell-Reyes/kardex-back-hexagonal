package com.kardex.domain.exception;

import java.util.List;

public class AvailableProvidersException extends RuntimeException {
    private final List<String> availableProviders;

    public AvailableProvidersException(List<String> availableProviders) {
        super();
        this.availableProviders = availableProviders;
    }

    public List<String> getAvailableProviders() {
        return availableProviders;
    }
}
