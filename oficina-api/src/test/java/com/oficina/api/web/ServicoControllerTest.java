package com.oficina.api.web;

import com.oficina.api.domain.entity.Servico;
import com.oficina.api.web.controller.ServicoController;
import com.oficina.api.application.ServicoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class ServicoControllerTest {
    private final ServicoService service = Mockito.mock(ServicoService.class);
    @Test
    void testGetServico() {
        Servico servico = Servico.builder().id(1L).nome("Troca de óleo").preco(BigDecimal.valueOf(100)).build();
        Mockito.when(service.findById(1L)).thenReturn(Optional.of(servico));
        ServicoController controller = new ServicoController(service);
        assertEquals("Troca de óleo", controller.getServico(1L).getNome());
    }
}
