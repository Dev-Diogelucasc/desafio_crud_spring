package com.devdioge.desafioCrud.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientDto {

    private Long id;
    private String name;
    private String cpf;
    private Double income;
    private LocalDate birthDate;
    private Integer children;
}
