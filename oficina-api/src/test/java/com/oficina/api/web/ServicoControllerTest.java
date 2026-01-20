package com.oficina.api.web;

import com.oficina.api.domain.Servico;
import com.oficina.api.domain.ServicoRepository;
import com.oficina.api.web.controller.ServicoController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class ServicoControllerTest {
    @Test
    void testGetServico() {
        ServicoRepository repo = Mockito.mock(ServicoRepository.class);
        Servico servico = Servico.builder().id(1L).nome("Troca de óleo").preco(BigDecimal.valueOf(100)).build();
        Mockito.when(repo.findById(1L)).thenReturn(servico);
        ServicoController controller = new ServicoController();
        controller.servicoRepository = repo;
        assertEquals("Troca de óleo", controller.getServico(1L).getNome());
    }
}

