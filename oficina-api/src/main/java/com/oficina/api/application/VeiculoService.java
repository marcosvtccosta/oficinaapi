package com.oficina.api.application;

import com.oficina.api.domain.entity.Veiculo;
import com.oficina.api.domain.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {
    private final VeiculoRepository veiculoRepository;

    @Autowired
    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public Veiculo findByPlaca(String placa) {
        return veiculoRepository.findByPlaca(placa);
    }

    public Veiculo save(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public void deleteByPlaca(String placa) {
        // Implementação de remoção, se necessário
    }
}
