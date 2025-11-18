package com.devdioge.desafioCrud.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Entity
@Data
@Table(name  = "tb_client")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private String cpf;
    private Double income;
    private LocalDate birthDate;
    private Integer children;
}
