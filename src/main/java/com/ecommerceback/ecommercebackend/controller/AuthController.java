package com.ecommerceback.ecommercebackend.controller;

import com.ecommerceback.ecommercebackend.dto.LoginUserDto;
import com.ecommerceback.ecommercebackend.dto.RegisterUser;
import com.ecommerceback.ecommercebackend.dto.UserResponseDto;
import com.ecommerceback.ecommercebackend.entity.ApplicationUsers;
import com.ecommerceback.ecommercebackend.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ApplicationUsers register(@RequestBody RegisterUser registerUser) {
        return authenticationService.register(registerUser.firstName(),
                registerUser.lastName(), registerUser.email(), registerUser.password());
    }

    @PostMapping("/login")
    public UserResponseDto login(@RequestBody LoginUserDto loginUserDto) {
        ApplicationUsers user = authenticationService.login(loginUserDto.email(), loginUserDto.password());
        return new UserResponseDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
    }

}
