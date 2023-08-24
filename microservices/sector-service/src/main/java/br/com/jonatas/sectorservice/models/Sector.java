package br.com.jonatas.sectorservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "sectors")
@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sector {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(name = "type_product")
    private String typeProduct;
    private UUID employeeId;
    @OneToMany(mappedBy = "sector", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> products;
}
