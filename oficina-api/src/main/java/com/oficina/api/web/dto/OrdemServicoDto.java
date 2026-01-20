package com.oficina.api.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdemServicoDto {
    private Long id;
    private ClienteDto cliente;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private List<ServicoDto> servicos;
    private List<ProdutoDto> produtos;
    private String status;
}
