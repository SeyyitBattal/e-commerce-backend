package com.ecommerceback.ecommercebackend.service;

import com.ecommerceback.ecommercebackend.entity.ApplicationUsers;
import com.ecommerceback.ecommercebackend.entity.Roles;
import com.ecommerceback.ecommercebackend.repository.RolesRepository;
import com.ecommerceback.ecommercebackend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthenticationService {
    private UsersRepository usersRepository;
    private RolesRepository rolesRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UsersRepository usersRepository, RolesRepository rolesRepository,
                                 PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ApplicationUsers register(String firstName, String lastName, String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        Roles userRole = rolesRepository.findByAuthority("Customer").get();
        Set<Roles> authorities = new HashSet<>();
        authorities.add(userRole);

        ApplicationUsers user = new ApplicationUsers();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setAuthorities(authorities);
        return usersRepository.save(user);
    }

    public ApplicationUsers login(String email, String password) {
        Optional<ApplicationUsers> optionalUser = usersRepository.findUserByEmail(email);
        if (optionalUser.isPresent()) {
            ApplicationUsers applicationUser = optionalUser.get();
            boolean isSame = passwordEncoder.matches(password, applicationUser.getPassword());
            if (isSame) {
                return applicationUser;
            }
            throw new RuntimeException("Invalid Credentials");
        }
        throw new RuntimeException("Invalid Credentials");
    }

}
