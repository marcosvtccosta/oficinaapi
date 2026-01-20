package com.oficina.api.web.controller;

import com.oficina.api.domain.Cliente;
import com.oficina.api.domain.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    public ClienteRepository clienteRepository;

    @GetMapping("/{id}")
    public Cliente getCliente(@PathVariable Long id) {
        return clienteRepository.findById(id);
    }

    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{id}")
    public Cliente updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        return clienteRepository.save(cliente);
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
}

