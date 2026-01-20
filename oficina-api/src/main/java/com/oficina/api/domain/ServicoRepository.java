package com.oficina.api.domain;

public interface ServicoRepository {
    Servico findById(Long id);
    Servico save(Servico servico);
    // outros métodos conforme necessidade
}

