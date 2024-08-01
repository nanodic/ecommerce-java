package com.example.ecommercefinal.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="products")
@NoArgsConstructor
@EqualsAndHashCode @ToString
public class Products {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Setter @Getter private Integer id;

    @Column
    @Setter @Getter private String description;
    @Column
    @Setter @Getter private String code;
    @Column
    @Setter @Getter private Integer stock;
    @Column
    @Setter @Getter private Double price;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Carts product_id;

}
