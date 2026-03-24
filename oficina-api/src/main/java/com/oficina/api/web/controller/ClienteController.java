package com.oficina.api.web.controller;

import com.oficina.api.application.ClienteService;
import com.oficina.api.domain.entity.Cliente;
import com.oficina.api.web.dto.ClienteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    @GetMapping("/{id}")
    public ClienteDto getCliente(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.findCliente(id);
        return toDto(cliente.orElse(null));
    }

    @PostMapping
    public ClienteDto createCliente(@RequestBody ClienteDto clienteDto) {
        Cliente cliente = toEntity(clienteDto);
        Cliente saved = clienteService.save(cliente);
        return toDto(saved);
    }

    @PutMapping("/{id}")
    public ClienteDto updateCliente(@PathVariable Long id, @RequestBody ClienteDto clienteDto) {
        Cliente cliente = toEntity(clienteDto);
        cliente.setId(id);
        Cliente updated = clienteService.save(cliente);
        return toDto(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Long id) {
        // Implementação de remoção
    }

    @PostMapping("/validar-documento")
    public boolean validarDocumento(@RequestParam String documento) {
        return isValidCpf(documento) || isValidCnpj(documento);
    }

    private boolean isValidCpf(String cpf) {
        // Regex simples para CPF: 11 dígitos
        return Pattern.matches("\\d{11}", cpf);
    }

    private boolean isValidCnpj(String cnpj) {
        // Regex simples para CNPJ: 14 dígitos
        return Pattern.matches("\\d{14}", cnpj);
    }

    private ClienteDto toDto(Cliente cliente) {
        if (cliente == null) return null;
        ClienteDto dto = new ClienteDto();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setCpfOuCnpj(cliente.getCpfOuCnpj());
        return dto;
    }

    private Cliente toEntity(ClienteDto dto) {
        if (dto == null) return null;
        Cliente cliente = new Cliente();
        cliente.setId(dto.getId());
        cliente.setNome(dto.getNome());
        cliente.setCpfOuCnpj(dto.getCpfOuCnpj());
        return cliente;
    }
}
