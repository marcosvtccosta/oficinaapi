package com.oficina.api.web.controller;

import com.oficina.api.domain.OrdemServico;
import com.oficina.api.domain.OrdemServicoRepository;
import com.oficina.api.domain.OrdemServicoStatus;
import com.oficina.api.web.dto.OrdemServicoDto;
import com.oficina.api.web.dto.ClienteDto;
import com.oficina.api.web.dto.ServicoDto;
import com.oficina.api.web.dto.ProdutoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.Duration;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

    private final OrdemServicoRepository ordemServicoRepository;

    @Autowired
    public OrdemServicoController(OrdemServicoRepository ordemServicoRepository) {
        this.ordemServicoRepository = ordemServicoRepository;
    }

    @GetMapping("/{id}")
    public OrdemServicoDto getOrdemServico(@PathVariable Long id) {
        OrdemServico ordemServico = ordemServicoRepository.findById(id);
        return toDto(ordemServico);
    }

    @PostMapping
    public OrdemServicoDto createOrdemServico(@RequestBody OrdemServicoDto ordemServicoDto) {
        OrdemServico ordemServico = toEntity(ordemServicoDto);
        OrdemServico saved = ordemServicoRepository.save(ordemServico);
        return toDto(saved);
    }

    @PutMapping("/{id}")
    public OrdemServicoDto updateOrdemServico(@PathVariable Long id, @RequestBody OrdemServicoDto ordemServicoDto) {
        OrdemServico ordemServico = toEntity(ordemServicoDto);
        ordemServico.setId(id);
        OrdemServico updated = ordemServicoRepository.save(ordemServico);
        return toDto(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteOrdemServico(@PathVariable Long id) {
        // Implementação de remoção
    }

    @PatchMapping("/{id}/status")
    public OrdemServicoDto alterarStatus(@PathVariable Long id, @RequestParam OrdemServicoStatus status) {
        OrdemServico ordem = ordemServicoRepository.findById(id);
        ordem.setStatus(status);
        OrdemServico updated = ordemServicoRepository.save(ordem);
        return toDto(updated);
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

    private OrdemServicoDto toDto(OrdemServico ordem) {
        if (ordem == null) return null;
        OrdemServicoDto dto = new OrdemServicoDto();
        dto.setId(ordem.getId());
        dto.setCliente(ordem.getCliente() != null ? toClienteDto(ordem.getCliente()) : null);
        dto.setDataInicio(ordem.getDataInicio());
        dto.setDataFim(ordem.getDataFim());
        dto.setServicos(ordem.getServicos() != null ? ordem.getServicos().stream().map(this::toServicoDto).toList() : null);
        dto.setProdutos(ordem.getProdutos() != null ? ordem.getProdutos().stream().map(this::toProdutoDto).toList() : null);
        dto.setStatus(ordem.getStatus() != null ? ordem.getStatus().name() : null);
        return dto;
    }
    private ClienteDto toClienteDto(com.oficina.api.domain.Cliente cliente) {
        ClienteDto dto = new ClienteDto();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setCpfOuCnpj(cliente.getCpfOuCnpj());
        return dto;
    }
    private ServicoDto toServicoDto(com.oficina.api.domain.Servico servico) {
        ServicoDto dto = new ServicoDto();
        dto.setId(servico.getId());
        dto.setNome(servico.getNome());
        dto.setPreco(servico.getPreco());
        return dto;
    }
    private ProdutoDto toProdutoDto(com.oficina.api.domain.Produto produto) {
        ProdutoDto dto = new ProdutoDto();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setPreco(produto.getPreco());
        dto.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        return dto;
    }
    private OrdemServico toEntity(OrdemServicoDto dto) {
        if (dto == null) return null;
        OrdemServico ordem = new OrdemServico();
        ordem.setId(dto.getId());
        ordem.setDataInicio(dto.getDataInicio());
        ordem.setDataFim(dto.getDataFim());
        if (dto.getStatus() != null) ordem.setStatus(OrdemServicoStatus.valueOf(dto.getStatus()));
        return ordem;
    }
}
