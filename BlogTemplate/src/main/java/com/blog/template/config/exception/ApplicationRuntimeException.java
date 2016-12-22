package com.blog.template.config.exception;

public class ApplicationRuntimeException extends Exception {
    
    private static final long serialVersionUID = 1L;
    
    public ApplicationRuntimeException(String message) {
        super(message);
    }
}
