package com.oficina.api.domain;

public interface OrdemServicoRepository {
    OrdemServico findById(Long id);
    OrdemServico save(OrdemServico ordemServico);
    // outros métodos conforme necessidade
}

