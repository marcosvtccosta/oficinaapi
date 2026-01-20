package com.oficina.api.application;

import com.oficina.api.domain.entity.OrdemServico;
import com.oficina.api.domain.entity.OrdemServicoStatus;
import com.oficina.api.domain.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrdemServicoService {
    private final OrdemServicoRepository ordemServicoRepository;

    @Autowired
    public OrdemServicoService(OrdemServicoRepository ordemServicoRepository) {
        this.ordemServicoRepository = ordemServicoRepository;
    }

    public Optional<OrdemServico> findById(Long id) {
        return ordemServicoRepository.findById(id);
    }

    public OrdemServico save(OrdemServico ordemServico) {
        return ordemServicoRepository.save(ordemServico);
    }

    public void deleteById(Long id) {
        ordemServicoRepository.deleteById(id);
    }
}
