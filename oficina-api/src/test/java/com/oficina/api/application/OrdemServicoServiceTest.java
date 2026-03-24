package com.oficina.api.application;

import com.oficina.api.domain.entity.OrdemServico;
import com.oficina.api.domain.entity.OrdemServicoStatus;
import com.oficina.api.domain.repository.OrdemServicoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrdemServicoServiceTest {

    private final OrdemServicoRepository repository = Mockito.mock(OrdemServicoRepository.class);
    private final OrdemServicoService service = new OrdemServicoService(repository);

    @Test
    void deveListarOrdensAbertasOrdenadasPorStatusEDataInicioDesc() {
        OrdemServico recebida = OrdemServico.builder()
                .id(1L)
                .status(OrdemServicoStatus.RECEBIDA)
                .dataInicio(LocalDateTime.of(2026, 3, 24, 9, 0))
                .build();

        OrdemServico emDiagnostico = OrdemServico.builder()
                .id(2L)
                .status(OrdemServicoStatus.EM_DIAGNOSTICO)
                .dataInicio(LocalDateTime.of(2026, 3, 24, 10, 0))
                .build();

        OrdemServico aguardandoAprovacao = OrdemServico.builder()
                .id(3L)
                .status(OrdemServicoStatus.AGUARDANDO_APROVACAO)
                .dataInicio(LocalDateTime.of(2026, 3, 24, 8, 0))
                .build();

        OrdemServico emExecucaoMaisNovo = OrdemServico.builder()
                .id(4L)
                .status(OrdemServicoStatus.EM_EXECUCAO)
                .dataInicio(LocalDateTime.of(2026, 3, 24, 12, 0))
                .build();

        OrdemServico emExecucaoMaisAntigo = OrdemServico.builder()
                .id(5L)
                .status(OrdemServicoStatus.EM_EXECUCAO)
                .dataInicio(LocalDateTime.of(2026, 3, 24, 7, 0))
                .build();

        when(repository.findByStatusNotIn(anyList())).thenReturn(List.of(
                recebida,
                emDiagnostico,
                aguardandoAprovacao,
                emExecucaoMaisAntigo,
                emExecucaoMaisNovo
        ));

        List<OrdemServico> resultado = service.listarOrdensAbertasOrdenadas();

        assertEquals(5, resultado.size());
        assertEquals(4L, resultado.get(0).getId());
        assertEquals(5L, resultado.get(1).getId());
        assertEquals(3L, resultado.get(2).getId());
        assertEquals(2L, resultado.get(3).getId());
        assertEquals(1L, resultado.get(4).getId());

        verify(repository).findByStatusNotIn(Mockito.argThat(status ->
                status.contains(OrdemServicoStatus.FINALIZADA) &&
                status.contains(OrdemServicoStatus.ENTREGUE)
        ));
    }
}

