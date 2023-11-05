package com.ecommerceback.ecommercebackend.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ProductsException extends RuntimeException {
    private HttpStatus status;

    public ProductsException(String message, HttpStatus httpStatus) {
        super(message);
        this.status = httpStatus;
    }
}
