package com.ecommerceback.ecommercebackend.exceptions;

import org.springframework.http.HttpStatus;

public class EcommerceValidation {

    public static void isIdValid(long id) {
        if (id <= 0) {
            throw new ProductsException("Id is not valid: " + id, HttpStatus.BAD_REQUEST);
        }
    }


}
