package com.oficina.api.web.controller;

import com.oficina.api.application.VeiculoService;
import com.oficina.api.domain.entity.PlacaRegexProvider;
import com.oficina.api.domain.entity.Veiculo;
import com.oficina.api.web.dto.VeiculoDto;
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

import java.util.regex.Pattern;

@RequiredArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    @GetMapping("/{placa}")
    public VeiculoDto getVeiculo(@PathVariable String placa) {
        Veiculo veiculo = veiculoService.findByPlaca(placa);
        return toDto(veiculo);
    }

    @PostMapping
    public VeiculoDto createVeiculo(@RequestBody VeiculoDto veiculoDto) {
        Veiculo veiculo = toEntity(veiculoDto);
        Veiculo saved = veiculoService.save(veiculo);
        return toDto(saved);
    }

    @PutMapping("/{placa}")
    public VeiculoDto updateVeiculo(@PathVariable String placa, @RequestBody VeiculoDto veiculoDto) {
        Veiculo veiculo = toEntity(veiculoDto);
        veiculo.setPlaca(placa);
        Veiculo updated = veiculoService.save(veiculo);
        return toDto(updated);
    }

    @DeleteMapping("/{placa}")
    public void deleteVeiculo(@PathVariable String placa) {
        veiculoService.deleteByPlaca(placa);
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
