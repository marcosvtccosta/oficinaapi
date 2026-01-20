package com.oficina.api.domain;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "OFICINAAPI", name = "ORDEM_SERVICO")
public class OrdemServico {
    private Long id;
    private Cliente cliente;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private List<Servico> servicos;
    private List<Produto> produtos;
    private OrdemServicoStatus status;
}
