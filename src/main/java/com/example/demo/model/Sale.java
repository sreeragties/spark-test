package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "sale")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sale {
//id, name, description, price, and quantity.

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Schema(hidden = true)
    private int saleId;
    @Column(name = "product_id")
    @Schema(hidden = true)
    private int productId;
    private int quantity;
    private LocalDate saleDate;
}
