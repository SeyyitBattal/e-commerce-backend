package com.ecommerceback.ecommercebackend.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ProductsErrorResponse> handleException(ProductsException productsException) {
        ProductsErrorResponse errorResponse = new ProductsErrorResponse(
                productsException.getStatus().value(), productsException.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, productsException.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<CategoriesErrorResponse> handleException(CategoriesException categoriesException) {
        CategoriesErrorResponse errorResponse = new CategoriesErrorResponse(
                categoriesException.getStatus().value(), categoriesException.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, categoriesException.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ProductsErrorResponse> handleException(Exception exception) {
        ProductsErrorResponse errorResponse = new ProductsErrorResponse(
                HttpStatus.BAD_REQUEST.value(), exception.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
