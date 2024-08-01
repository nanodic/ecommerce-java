package com.example.ecommercefinal.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="invoicesDetails")
@NoArgsConstructor
@EqualsAndHashCode @ToString
public class InvoiceDetails {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Setter @Getter private Integer invoce_detail_id;

    @Column(insertable = false, updatable = false)
    @Setter @Getter private Integer invoice_id;
    @Column
    @Setter @Getter private Integer amount;
    @Column(insertable=false, updatable=false)
    @Setter @Getter private Integer product_id;
    @Column
    @Setter @Getter private Double price;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Clients client_id;
}
