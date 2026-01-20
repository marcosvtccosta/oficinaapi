package com.oficina.api.web.controller;

import com.oficina.api.domain.Produto;
import com.oficina.api.domain.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    public ProdutoRepository produtoRepository;

    @GetMapping("/{id}")
    public Produto getProduto(@PathVariable Long id) {
        return produtoRepository.findById(id);
    }

    @PostMapping
    public Produto createProduto(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @PutMapping("/{id}")
    public Produto updateProduto(@PathVariable Long id, @RequestBody Produto produto) {
        produto.setId(id);
        return produtoRepository.save(produto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduto(@PathVariable Long id) {
        // Implementação de remoção
    }

    @PostMapping("/{id}/adicionar-estoque")
    public Produto adicionarEstoque(@PathVariable Long id, @RequestParam int quantidade) {
        Produto produto = produtoRepository.findById(id);
        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + quantidade);
        return produtoRepository.save(produto);
    }

    @PostMapping("/{id}/remover-estoque")
    public Produto removerEstoque(@PathVariable Long id, @RequestParam int quantidade) {
        Produto produto = produtoRepository.findById(id);
        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
        return produtoRepository.save(produto);
    }
}

