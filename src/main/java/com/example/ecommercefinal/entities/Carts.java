package com.example.ecommercefinal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="carts")
@NoArgsConstructor
@EqualsAndHashCode @ToString
@Schema(description = "Representa un Carrito")
public class Carts {

    @Id
    @Schema (description = "Identificador unico de carrito", example = "1")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Setter @Getter private Integer Id;

    @Schema (description = "Cantidad de producto elegido", example = "3")
    @Column
    @Setter @Getter private Integer amount;
    @Schema (description = "Precio individual del producto", example = "1500")
    @Column
    @Setter @Getter private Double price;
    @Schema (description = "Estado del carrito (entregado / no entregado)", example = "false")
    @Column
    @Setter @Getter private boolean delivered;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore
    @Setter @Getter private Clients client;

    @ManyToOne
    @JoinColumn(name = "product_Id")
    @JsonIgnore
    @Setter @Getter private Products products;


}
