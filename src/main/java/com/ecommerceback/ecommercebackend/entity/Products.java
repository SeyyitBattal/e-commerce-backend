package com.ecommerceback.ecommercebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products", schema = "myschema")
public class Products {

    public Products(long id, String name, String description, Color color, Gender gender, double rating, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.color = color;
        this.gender = gender;
        this.rating = rating;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    private Color color;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "rating")
    private double rating;

    @Column(name = "price")
    private double price;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "category_id")
    private Categories categories;

}
