package com.ecommerceback.ecommercebackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "categories", schema = "myschema")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    @Size(min = 2)
    private String name;

    @Enumerated(EnumType.STRING)
    @Size(min = 3, max = 15, message = "Gender must be between 3 to 15")
    private Gender gender;

    @OneToMany(mappedBy = "categories", cascade = CascadeType.ALL)
    private List<Products> productsList = new ArrayList<>();

    public void addProduct(Products product) {
        if (productsList != null) {
            productsList = new ArrayList<>();
        }
        productsList.add(product);
    }

}
