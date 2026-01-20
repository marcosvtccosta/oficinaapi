package com.oficina.api.domain;

public interface ClienteRepository {
    Cliente findById(Long id);
    Cliente save(Cliente cliente);
    // outros métodos conforme necessidade
}

