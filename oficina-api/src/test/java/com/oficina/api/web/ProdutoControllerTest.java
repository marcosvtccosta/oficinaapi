package com.oficina.api.web;

import com.oficina.api.domain.Produto;
import com.oficina.api.domain.ProdutoRepository;
import com.oficina.api.web.controller.ProdutoController;
import com.oficina.api.web.dto.ProdutoDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class ProdutoControllerTest {
    private final ProdutoRepository repo = Mockito.mock(ProdutoRepository.class);
    @Test
    void testAdicionarEstoque() {
        Produto produto = Produto.builder().id(1L).nome("Filtro").preco(BigDecimal.valueOf(50)).quantidadeEstoque(10).build();
        Mockito.when(repo.findById(1L)).thenReturn(produto);
        Mockito.when(repo.save(Mockito.any())).thenAnswer(i -> i.getArgument(0));
        ProdutoController controller = new ProdutoController(repo);
        ProdutoDto atualizado = controller.adicionarEstoque(1L, 5);
        assertEquals(15, atualizado.getQuantidadeEstoque());
    }
    @Test
    void testRemoverEstoque() {
        Produto produto = Produto.builder().id(1L).nome("Filtro").preco(BigDecimal.valueOf(50)).quantidadeEstoque(10).build();
        Mockito.when(repo.findById(1L)).thenReturn(produto);
        Mockito.when(repo.save(Mockito.any())).thenAnswer(i -> i.getArgument(0));
        ProdutoController controller = new ProdutoController(repo);
        ProdutoDto atualizado = controller.removerEstoque(1L, 3);
        assertEquals(7, atualizado.getQuantidadeEstoque());
    }
}

