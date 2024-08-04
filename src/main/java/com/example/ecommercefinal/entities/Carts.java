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
    @Setter @Getter private Integer Id;

    @Column
    @Setter @Getter private Integer amount;
    @Column
    @Setter @Getter private Double price;
    @Column
    @Setter @Getter private boolean delivered;

    @ManyToOne
    @JoinColumn(name = "clientId")
    @Setter @Getter private Clients clientId;

    @ManyToOne
    @JoinColumn(name = "productId")
    @Setter @Getter private Products productId;

}
