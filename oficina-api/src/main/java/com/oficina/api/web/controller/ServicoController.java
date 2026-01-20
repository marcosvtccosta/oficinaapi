package com.oficina.api.web.controller;

import com.oficina.api.domain.Servico;
import com.oficina.api.domain.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servicos")
public class ServicoController {
    @Autowired
    public  ServicoRepository servicoRepository;

    @GetMapping("/{id}")
    public Servico getServico(@PathVariable Long id) {
        return servicoRepository.findById(id);
    }

    @PostMapping
    public Servico createServico(@RequestBody Servico servico) {
        return servicoRepository.save(servico);
    }

    @PutMapping("/{id}")
    public Servico updateServico(@PathVariable Long id, @RequestBody Servico servico) {
        servico.setId(id);
        return servicoRepository.save(servico);
    }

    @DeleteMapping("/{id}")
    public void deleteServico(@PathVariable Long id) {
        // Implementação de remoção
    }
}

