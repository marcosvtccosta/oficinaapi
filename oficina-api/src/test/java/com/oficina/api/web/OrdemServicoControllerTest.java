package com.oficina.api.web;

import com.oficina.api.domain.entity.OrdemServico;
import com.oficina.api.domain.entity.OrdemServicoStatus;
import com.oficina.api.web.controller.OrdemServicoController;
import com.oficina.api.web.dto.OrdemServicoDto;
import com.oficina.api.application.OrdemServicoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class OrdemServicoControllerTest {
    private final OrdemServicoService service = Mockito.mock(OrdemServicoService.class);

    @Test
    void testAlterarStatus() {
        OrdemServico ordem = OrdemServico.builder().id(1L).status(OrdemServicoStatus.RECEBIDA).build();
        Mockito.when(service.findById(1L)).thenReturn(Optional.of(ordem));
        Mockito.when(service.save(Mockito.any())).thenAnswer(i -> i.getArgument(0));
        OrdemServicoController controller = new OrdemServicoController(service);

        OrdemServicoDto atualizado = controller.alterarStatus(1L, OrdemServicoStatus.FINALIZADA);
        assertEquals(OrdemServicoStatus.FINALIZADA.name(), atualizado.getStatus());
    }

    @Test
    void testTempoGasto() {
        LocalDateTime inicio = LocalDateTime.of(2026, 1, 19, 8, 0);
        LocalDateTime fim = LocalDateTime.of(2026, 1, 19, 10, 30);
        OrdemServico ordem = OrdemServico.builder().id(1L).dataInicio(inicio).dataFim(fim).build();
        Mockito.when(service.findById(1L)).thenReturn(Optional.of(ordem));
        OrdemServicoController controller = new OrdemServicoController(service);

        String tempo = controller.tempoGasto(1L);
        assertTrue(tempo.contains("2 horas e 30 minutos"));
    }
}
