package br.com.jonatas.sectorservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String description;
    private String barcode;
    private Double price;
    private Long quantity;
    @ManyToOne
    @JoinColumn(name = "sector")
    private Sector sector;
}
