package com.ecommerceback.ecommercebackend.exceptions;

import com.ecommerceback.ecommercebackend.entity.Products;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class EcommerceValidation {

    public static void isIdValid(long id) {
        if (id <= 0) {
            throw new ProductsException("Id is not valid: " + id, HttpStatus.BAD_REQUEST);
        }
    }


}
