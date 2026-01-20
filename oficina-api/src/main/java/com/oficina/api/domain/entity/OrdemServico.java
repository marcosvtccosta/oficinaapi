package com.oficina.api.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "ORDEM_SERVICO")
public class OrdemServico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID")
    private Cliente cliente;
    @Column(name = "DATA_INICIO", columnDefinition = "DATETIME")
    private LocalDateTime dataInicio;
    @Column(name = "DATA_FIM", columnDefinition = "DATETIME")
    private LocalDateTime dataFim;
    @ManyToMany
    @JoinTable(
        name = "ORDEM_SERVICO_SERVICO",
        joinColumns = @JoinColumn(name = "ORDEM_SERVICO_ID"),
        inverseJoinColumns = @JoinColumn(name = "SERVICO_ID")
    )
    private List<Servico> servicos;
    @ManyToMany
    @JoinTable(
        name = "ORDEM_SERVICO_PRODUTO",
        joinColumns = @JoinColumn(name = "ORDEM_SERVICO_ID"),
        inverseJoinColumns = @JoinColumn(name = "PRODUTO_ID")
    )
    private List<Produto> produtos;
    @Column(name = "STATUS", length = 30)
    private OrdemServicoStatus status;
}
