package com.example.ecommercefinal.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

//TAG de entidad
@Entity
@Table(name="clients")
@NoArgsConstructor @EqualsAndHashCode @ToString
public class Clients {

    //Id autogenerado - ClavePrimaria
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Setter @Getter private Integer id;

    //Definicion de columnas de la tabla
    @Column
    @Setter @Getter private String name;
    @Column
    @Setter @Getter private String lastName;
    @Column
    @Setter @Getter private Integer document;

    @OneToMany(mappedBy = "client_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Carts> carts;

    @OneToMany(mappedBy = "client_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invoice> invoice;

}