package com.oficina.api.web.controller;

import com.oficina.api.domain.Cliente;
import com.oficina.api.domain.ClienteRepository;
import com.oficina.api.web.dto.ClienteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/{id}")
    public ClienteDto getCliente(@PathVariable Long id) {
        Cliente cliente = clienteRepository.findById(id);
        return toDto(cliente);
    }

    @PostMapping
    public ClienteDto createCliente(@RequestBody ClienteDto clienteDto) {
        Cliente cliente = toEntity(clienteDto);
        Cliente saved = clienteRepository.save(cliente);
        return toDto(saved);
    }

    @PutMapping("/{id}")
    public ClienteDto updateCliente(@PathVariable Long id, @RequestBody ClienteDto clienteDto) {
        Cliente cliente = toEntity(clienteDto);
        cliente.setId(id);
        Cliente updated = clienteRepository.save(cliente);
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
