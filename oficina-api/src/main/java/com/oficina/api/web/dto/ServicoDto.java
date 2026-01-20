package com.oficina.api.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicoDto {
    private Long id;
    private String nome;
    private double preco;
}
