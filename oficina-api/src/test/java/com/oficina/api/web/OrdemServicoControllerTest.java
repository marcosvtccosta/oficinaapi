package com.oficina.api.web;

import com.oficina.api.domain.entity.OrdemServico;
import com.oficina.api.domain.entity.OrdemServicoStatus;
import com.oficina.api.domain.entity.Cliente;
import com.oficina.api.web.controller.OrdemServicoController;
import com.oficina.api.web.dto.OrdemServicoDto;
import com.oficina.api.web.dto.OrdemServicoStatusResponseDto;
import com.oficina.api.application.OrdemServicoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;
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

        OrdemServicoStatusResponseDto atualizado = controller.alterarStatusViaPost(1L, OrdemServicoStatus.FINALIZADA);
        assertEquals(OrdemServicoStatus.FINALIZADA.name(), atualizado.getStatus());
    }

    @Test
    void testAlterarStatusViaPostComSucesso() {
        OrdemServico ordem = OrdemServico.builder().id(1L).status(OrdemServicoStatus.FINALIZADA).build();
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente Teste");
        ordem.setCliente(cliente);
        Mockito.when(service.atualizarStatusComRetorno(1L, OrdemServicoStatus.FINALIZADA)).thenReturn(Optional.of(ordem));
        OrdemServicoController controller = new OrdemServicoController(service);

        OrdemServicoStatusResponseDto resposta = controller.alterarStatusViaPost(1L, OrdemServicoStatus.FINALIZADA);

        assertNotNull(resposta);
        assertEquals(1L, resposta.getIdOrdemServico());
        assertEquals("Cliente Teste", resposta.getNomeCliente());
        assertEquals("FINALIZADA", resposta.getStatus());
    }

    @Test
    void testAlterarStatusViaPostComErroRetornaNull() {
        Mockito.when(service.atualizarStatusComRetorno(1L, OrdemServicoStatus.FINALIZADA)).thenReturn(Optional.empty());
        OrdemServicoController controller = new OrdemServicoController(service);

        OrdemServicoStatusResponseDto resposta = controller.alterarStatusViaPost(1L, OrdemServicoStatus.FINALIZADA);

        assertNull(resposta);
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

    @Test
    void testGetStatusOrdemServico() {
        OrdemServico ordem = OrdemServico.builder().id(1L).status(OrdemServicoStatus.RECEBIDA).build();
        Mockito.when(service.findById(1L)).thenReturn(Optional.of(ordem));
        OrdemServicoController controller = new OrdemServicoController(service);

        String status = controller.getStatusOrdemServico(1L);
        assertEquals("RECEBIDA", status);
    }

    @Test
    void testListarOrdensAbertasOrdenadas() {
        OrdemServico ordem1 = OrdemServico.builder().id(1L).status(OrdemServicoStatus.EM_EXECUCAO).build();
        OrdemServico ordem2 = OrdemServico.builder().id(2L).status(OrdemServicoStatus.RECEBIDA).build();
        Mockito.when(service.listarOrdensAbertasOrdenadas()).thenReturn(List.of(ordem1, ordem2));
        OrdemServicoController controller = new OrdemServicoController(service);

        List<OrdemServicoDto> retorno = controller.listarOrdensAbertasOrdenadas();

        assertEquals(2, retorno.size());
        assertEquals(1L, retorno.get(0).getId());
        assertEquals("EM_EXECUCAO", retorno.get(0).getStatus());
    }
}
