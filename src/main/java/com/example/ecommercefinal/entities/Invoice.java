package com.example.ecommercefinal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="invoices")
@NoArgsConstructor
@EqualsAndHashCode @ToString
@Schema(description = "Representa una factura")
public class Invoice {

    @Id
    @Schema (description = "Identificador unico de factura", example = "1")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter @Getter private Integer id;
    @Schema (description = "Fecha de facturacion")
    @Column
    @Setter @Getter private Date createdAt;
    @Schema (description = "Precio total a facturar", example = "12000")
    @Column
    @Setter @Getter private Double total;


    @ManyToOne
    @JoinColumn(name = "client_Id")
    @JsonIgnore
    @Setter @Getter private Clients client;

}
