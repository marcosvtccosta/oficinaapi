package com.oficina.api.web.controller;

import com.oficina.api.domain.Veiculo;
import com.oficina.api.domain.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
    @Autowired
    public VeiculoRepository veiculoRepository;

    @GetMapping("/{placa}")
    public Veiculo getVeiculo(@PathVariable String placa) {
        return veiculoRepository.findByPlaca(placa);
    }

    @PostMapping
    public Veiculo createVeiculo(@RequestBody Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    @PutMapping("/{placa}")
    public Veiculo updateVeiculo(@PathVariable String placa, @RequestBody Veiculo veiculo) {
        veiculo.setPlaca(placa);
        return veiculoRepository.save(veiculo);
    }

    @DeleteMapping("/{placa}")
    public void deleteVeiculo(@PathVariable String placa) {
        // Implementação de remoção
    }

    @PostMapping("/validar-placa")
    public boolean validarPlaca(@RequestParam String placa) {
        return isValidPlaca(placa);
    }

    private boolean isValidPlaca(String placa) {
        // Regex para placas Mercosul e antigas
        String regexMercosul = "^[A-Z]{3}[0-9][A-Z][0-9]{2}$";
        String regexAntiga = "^[A-Z]{3}[0-9]{4}$";
        return Pattern.matches(regexMercosul, placa) || Pattern.matches(regexAntiga, placa);
    }
}

