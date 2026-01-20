package com.oficina.api.web;

import com.oficina.api.domain.OrdemServico;
import com.oficina.api.domain.OrdemServicoRepository;
import com.oficina.api.domain.OrdemServicoStatus;
import com.oficina.api.web.controller.OrdemServicoController;
import com.oficina.api.web.dto.OrdemServicoDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class OrdemServicoControllerTest {
    private final OrdemServicoRepository repo = Mockito.mock(OrdemServicoRepository.class);
    @Test
    void testAlterarStatus() {
        OrdemServico ordem = OrdemServico.builder().id(1L).status(OrdemServicoStatus.RECEBIDA).build();
        Mockito.when(repo.findById(1L)).thenReturn(ordem);
        Mockito.when(repo.save(Mockito.any())).thenAnswer(i -> i.getArgument(0));
        OrdemServicoController controller = new OrdemServicoController(repo);

        OrdemServicoDto atualizado = controller.alterarStatus(1L, OrdemServicoStatus.FINALIZADA);
        assertEquals(OrdemServicoStatus.FINALIZADA, atualizado.getStatus());
    }
    @Test
    void testTempoGasto() {
        LocalDateTime inicio = LocalDateTime.of(2026, 1, 19, 8, 0);
        LocalDateTime fim = LocalDateTime.of(2026, 1, 19, 10, 30);
        OrdemServico ordem = OrdemServico.builder().id(1L).dataInicio(inicio).dataFim(fim).build();
        Mockito.when(repo.findById(1L)).thenReturn(ordem);
        OrdemServicoController controller = new OrdemServicoController(repo);

        String tempo = controller.tempoGasto(1L);
        assertTrue(tempo.contains("2 horas e 30 minutos"));
    }
}

