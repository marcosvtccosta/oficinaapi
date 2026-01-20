package com.oficina.api.web;

import com.oficina.api.domain.Veiculo;
import com.oficina.api.domain.VeiculoRepository;
import com.oficina.api.web.controller.VeiculoController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

class VeiculoControllerTest {
    private  final VeiculoRepository repo = Mockito.mock(VeiculoRepository.class);
    @Test
    void testGetVeiculo() {
        Veiculo veiculo = Veiculo.builder().placa("ABC1234").marca("Ford").modelo("Fiesta").ano(2020).build();
        Mockito.when(repo.findByPlaca("ABC1234")).thenReturn(veiculo);
        VeiculoController controller = new VeiculoController(repo);
        assertEquals("Ford", controller.getVeiculo("ABC1234").getMarca());
    }
}

