package com.oficina.api.web;

import com.oficina.api.domain.entity.Veiculo;
import com.oficina.api.web.controller.VeiculoController;
import com.oficina.api.application.VeiculoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

class VeiculoControllerTest {
    private final VeiculoService service = Mockito.mock(VeiculoService.class);
    @Test
    void testGetVeiculo() {
        Veiculo veiculo = Veiculo.builder().placa("ABC1234").marca("Ford").modelo("Fiesta").ano(2020).build();
        Mockito.when(service.findByPlaca("ABC1234")).thenReturn(veiculo);
        VeiculoController controller = new VeiculoController(service);
        assertEquals("Ford", controller.getVeiculo("ABC1234").getMarca());
    }
}
