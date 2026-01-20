package com.oficina.api.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoDto {
    private String placa;
    private String marca;
    private String modelo;
    private int ano;
}
