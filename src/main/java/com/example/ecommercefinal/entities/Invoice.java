package com.example.ecommercefinal.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="invoices")
@NoArgsConstructor
@EqualsAndHashCode @ToString
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private Integer id;

    @Column
    @Setter
    @Getter
    private Date createdAt;
    @Column
    @Setter
    @Getter
    private Double total;


    @ManyToOne
    @JoinColumn(name = "client_Id")
    @Setter @Getter private Clients client;


}
