package com.oficina.api.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdemServicoStatusResponseDto {
    private Long idOrdemServico;
    private String nomeCliente;
    private String status;
}
