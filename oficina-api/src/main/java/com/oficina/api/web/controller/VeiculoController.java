package com.oficina.api.web.controller;

import com.oficina.api.domain.Veiculo;
import com.oficina.api.domain.VeiculoRepository;
import com.oficina.api.web.dto.VeiculoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.regex.Pattern;
import com.oficina.api.domain.PlacaRegexProvider;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoRepository veiculoRepository;
    @Autowired
     public VeiculoController(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    @GetMapping("/{placa}")
    public VeiculoDto getVeiculo(@PathVariable String placa) {
        Veiculo veiculo = veiculoRepository.findByPlaca(placa);
        return toDto(veiculo);
    }

    @PostMapping
    public VeiculoDto createVeiculo(@RequestBody VeiculoDto veiculoDto) {
        Veiculo veiculo = toEntity(veiculoDto);
        Veiculo saved = veiculoRepository.save(veiculo);
        return toDto(saved);
    }

    @PutMapping("/{placa}")
    public VeiculoDto updateVeiculo(@PathVariable String placa, @RequestBody VeiculoDto veiculoDto) {
        Veiculo veiculo = toEntity(veiculoDto);
        veiculo.setPlaca(placa);
        Veiculo updated = veiculoRepository.save(veiculo);
        return toDto(updated);
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
        String regexMercosul = PlacaRegexProvider.REGEX_MERCOSUL;
        String regexAntiga = PlacaRegexProvider.REGEX_ANTIGA;
        return Pattern.matches(regexMercosul, placa) || Pattern.matches(regexAntiga, placa);
    }

    private VeiculoDto toDto(Veiculo veiculo) {
        if (veiculo == null) return null;
        VeiculoDto dto = new VeiculoDto();
        dto.setPlaca(veiculo.getPlaca());
        dto.setMarca(veiculo.getMarca());
        dto.setModelo(veiculo.getModelo());
        dto.setAno(veiculo.getAno());
        return dto;
    }
    private Veiculo toEntity(VeiculoDto dto) {
        if (dto == null) return null;
        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca(dto.getPlaca());
        veiculo.setMarca(dto.getMarca());
        veiculo.setModelo(dto.getModelo());
        veiculo.setAno(dto.getAno());
        return veiculo;
    }
}
