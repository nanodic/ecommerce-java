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
    @Column
    @Setter @Getter private Double price;
    @Column
    @Setter @Getter private boolean delivered;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @Setter @Getter private Clients client_id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @Setter @Getter private Products product_id;

}
