package com.oficina.api.web.controller;

import com.oficina.api.application.ServicoService;
import com.oficina.api.domain.entity.Servico;
import com.oficina.api.web.dto.ServicoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/servicos")
public class ServicoController {

    private final ServicoService servicoService;


    @GetMapping("/{id}")
    public ServicoDto getServico(@PathVariable Long id) {
        Servico servico = servicoService.findById(id).orElse(null);
        return toDto(servico);
    }

    @PostMapping
    public ServicoDto createServico(@RequestBody ServicoDto servicoDto) {
        Servico servico = toEntity(servicoDto);
        Servico saved = servicoService.save(servico);
        return toDto(saved);
    }

    @PutMapping("/{id}")
    public ServicoDto updateServico(@PathVariable Long id, @RequestBody ServicoDto servicoDto) {
        Servico servico = toEntity(servicoDto);
        servico.setId(id);
        Servico updated = servicoService.save(servico);
        return toDto(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteServico(@PathVariable Long id) {
        servicoService.deleteById(id);
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
