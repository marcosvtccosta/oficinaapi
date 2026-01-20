package com.oficina.api.web.controller;

import com.oficina.api.domain.Servico;
import com.oficina.api.domain.ServicoRepository;
import com.oficina.api.web.dto.ServicoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    private  final  ServicoRepository servicoRepository;

    @Autowired
    public ServicoController(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    @GetMapping("/{id}")
    public ServicoDto getServico(@PathVariable Long id) {
        Servico servico = servicoRepository.findById(id);
        return toDto(servico);
    }

    @PostMapping
    public ServicoDto createServico(@RequestBody ServicoDto servicoDto) {
        Servico servico = toEntity(servicoDto);
        Servico saved = servicoRepository.save(servico);
        return toDto(saved);
    }

    @PutMapping("/{id}")
    public ServicoDto updateServico(@PathVariable Long id, @RequestBody ServicoDto servicoDto) {
        Servico servico = toEntity(servicoDto);
        servico.setId(id);
        Servico updated = servicoRepository.save(servico);
        return toDto(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteServico(@PathVariable Long id) {
        // Implementação de remoção
    }

    private ServicoDto toDto(Servico servico) {
        if (servico == null) return null;
        ServicoDto dto = new ServicoDto();
        dto.setId(servico.getId());
        dto.setNome(servico.getNome());
        dto.setPreco(servico.getPreco());
        return dto;
    }
    private Servico toEntity(ServicoDto dto) {
        if (dto == null) return null;
        Servico servico = new Servico();
        servico.setId(dto.getId());
        servico.setNome(dto.getNome());
        servico.setPreco(dto.getPreco());
        return servico;
    }
}
