package com.example.ecommercefinal.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="carts")
@NoArgsConstructor
@EqualsAndHashCode @ToString
public class Carts {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Setter @Getter private Integer cart_id;

    @Column
    @Setter @Getter private Integer amount;
    @Column(insertable=false, updatable=false)
    @Setter @Getter private Integer product_id;
    @Column
    @Setter @Getter private Double price;
    @Column
    @Setter @Getter private boolean delivered;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Clients client_id;

    @OneToMany(mappedBy = "product_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Products> products;

}
