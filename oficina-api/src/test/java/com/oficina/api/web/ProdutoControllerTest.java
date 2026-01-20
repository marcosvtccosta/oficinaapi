package com.oficina.api.web;

import com.oficina.api.domain.entity.Produto;
import com.oficina.api.web.controller.ProdutoController;
import com.oficina.api.web.dto.ProdutoDto;
import com.oficina.api.application.ProdutoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class ProdutoControllerTest {
    private final ProdutoService service = Mockito.mock(ProdutoService.class);

    @Test
    void testAdicionarEstoque() {
        Produto produto = Produto.builder().id(1L).nome("Filtro").preco(BigDecimal.valueOf(50)).quantidadeEstoque(10).build();
        Mockito.when(service.findById(1L)).thenReturn(Optional.of(produto));
        Mockito.when(service.save(Mockito.any())).thenAnswer(i -> i.getArgument(0));
        ProdutoController controller = new ProdutoController(service);
        ProdutoDto atualizado = controller.adicionarEstoque(1L, 5);
        assertEquals(15, atualizado.getQuantidadeEstoque());
    }

    @Test
    void testRemoverEstoque() {
        Produto produto = Produto.builder().id(1L).nome("Filtro").preco(BigDecimal.valueOf(50)).quantidadeEstoque(10).build();
        Mockito.when(service.findById(1L)).thenReturn(Optional.of(produto));
        Mockito.when(service.save(Mockito.any())).thenAnswer(i -> i.getArgument(0));
        ProdutoController controller = new ProdutoController(service);
        ProdutoDto atualizado = controller.removerEstoque(1L, 3);
        assertEquals(7, atualizado.getQuantidadeEstoque());
    }
}
