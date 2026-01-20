package com.oficina.api.domain;

public interface VeiculoRepository {
    Veiculo findByPlaca(String placa);
    Veiculo save(Veiculo veiculo);
    // outros métodos conforme necessidade
}

