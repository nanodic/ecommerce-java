package com.example.ecommercefinal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="products")
@NoArgsConstructor
@EqualsAndHashCode @ToString
@Schema(description = "Representa a un producto")
public class Products {

    @Id
    @Schema (description = "Identificador unico de producto", example = "1")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Setter @Getter private Integer id;

    @Schema (description = "Descripcion del producto", example = "YERBA AMANDA 1KG")
    @Column
    @Setter @Getter @NotNull(message = "Code cannot be null") private String description;
    @Schema (description = "Codigo de producto", example = "100")

    @Column
    @Setter @Getter @NotNull(message = "Code cannot be null") private String code;
    @Schema (description = "Stock disponible del producto", example = "50")

    @Column
    @Setter @Getter @NotNull(message = "Code cannot be null") private Integer stock;
    @Schema (description = "Precio", example = "1500")

    @Column
    @Setter @Getter @NotNull(message = "Code cannot be null") private Double price;


}
