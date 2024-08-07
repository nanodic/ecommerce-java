package com.example.ecommercefinal.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

//TAG de entidad
@Entity
@Table(name="clients")
@NoArgsConstructor @EqualsAndHashCode @ToString
@Schema (description = "Representa un Cliente")
public class Clients {

    //Id autogenerado - ClavePrimaria

    @Id
    @Schema (description = "Identificador unico de cliente", example = "1")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Setter @Getter private Integer Id;

    //Definicion de columnas de la tabla
    @Schema (description = "Nombre de cliente", example = "NAHUEL")
    @Column
    @Setter @Getter @NotNull(message = "Code cannot be null") private String name;
    @Schema (description = "Apellido cliente", example = "DI CIOCCO")
    @Column
    @Setter @Getter @NotNull(message = "Code cannot be null") private String lastName;
    @Schema (description = "Nro Documento", example = "33924213")
    @Column
    @Setter @Getter @NotNull(message = "Code cannot be null") private Integer document;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter @Getter @NotNull(message = "Code cannot be null") private List<Carts> carts;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter @Getter private List<Invoice> invoice;

}