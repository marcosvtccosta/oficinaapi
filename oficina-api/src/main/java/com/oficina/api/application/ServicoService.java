package com.oficina.api.application;

import com.oficina.api.domain.entity.Servico;
import com.oficina.api.domain.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicoService {
    private final ServicoRepository servicoRepository;

    @Autowired
    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public Optional<Servico> findById(Long id) {
        return servicoRepository.findById(id);
    }

    public Servico save(Servico servico) {
        return servicoRepository.save(servico);
    }

    public void deleteById(Long id) {
        servicoRepository.deleteById(id);
    }
}
