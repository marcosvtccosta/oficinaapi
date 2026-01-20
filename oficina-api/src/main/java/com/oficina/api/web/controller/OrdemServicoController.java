package com.oficina.api.web.controller;

import com.oficina.api.domain.OrdemServico;
import com.oficina.api.domain.OrdemServicoRepository;
import com.oficina.api.domain.OrdemServicoStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.Duration;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {
    @Autowired
    public OrdemServicoRepository ordemServicoRepository;

    @GetMapping("/{id}")
    public OrdemServico getOrdemServico(@PathVariable Long id) {
        return ordemServicoRepository.findById(id);
    }

    @PostMapping
    public OrdemServico createOrdemServico(@RequestBody OrdemServico ordemServico) {
        return ordemServicoRepository.save(ordemServico);
    }

    @PutMapping("/{id}")
    public OrdemServico updateOrdemServico(@PathVariable Long id, @RequestBody OrdemServico ordemServico) {
        ordemServico.setId(id);
        return ordemServicoRepository.save(ordemServico);
    }

    @DeleteMapping("/{id}")
    public void deleteOrdemServico(@PathVariable Long id) {
        // Implementação de remoção
    }

    @PatchMapping("/{id}/status")
    public OrdemServico alterarStatus(@PathVariable Long id, @RequestParam OrdemServicoStatus status) {
        OrdemServico ordem = ordemServicoRepository.findById(id);
        ordem.setStatus(status);
        return ordemServicoRepository.save(ordem);
    }

    @GetMapping("/{id}/tempo-gasto")
    public String tempoGasto(@PathVariable Long id) {
        OrdemServico ordem = ordemServicoRepository.findById(id);
        if (ordem.getDataInicio() != null && ordem.getDataFim() != null) {
            Duration duration = Duration.between(ordem.getDataInicio(), ordem.getDataFim());
            long horas = duration.toHours();
            long minutos = duration.toMinutes() % 60;
            return horas + " horas e " + minutos + " minutos";
        }
        return "Tempo não disponível";
    }
}

