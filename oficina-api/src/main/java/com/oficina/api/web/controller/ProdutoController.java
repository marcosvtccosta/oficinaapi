package com.oficina.api.web.controller;

import com.oficina.api.application.ProdutoService;
import com.oficina.api.domain.entity.Produto;
import com.oficina.api.web.dto.ProdutoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/{id}")
    public ProdutoDto getProduto(@PathVariable Long id) {
        Produto produto = produtoService.findById(id).orElse(null);
        return toDto(produto);
    }

    @PostMapping
    public ProdutoDto createProduto(@RequestBody ProdutoDto produtoDto) {
        Produto produto = toEntity(produtoDto);
        Produto saved = produtoService.save(produto);
        return toDto(saved);
    }

    @PutMapping("/{id}")
    public ProdutoDto updateProduto(@PathVariable Long id, @RequestBody ProdutoDto produtoDto) {
        Produto produto = toEntity(produtoDto);
        produto.setId(id);
        Produto updated = produtoService.save(produto);
        return toDto(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteProduto(@PathVariable Long id) {
        produtoService.deleteById(id);
    }

    @PostMapping("/{id}/adicionar-estoque")
    public ProdutoDto adicionarEstoque(@PathVariable Long id, @RequestParam int quantidade) {
        Produto produto = produtoService.findById(id).orElse(null);
        if (produto == null) return null;
        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + quantidade);
        Produto updated = produtoService.save(produto);
        return toDto(updated);
    }

    @PostMapping("/{id}/remover-estoque")
    public ProdutoDto removerEstoque(@PathVariable Long id, @RequestParam int quantidade) {
        Produto produto = produtoService.findById(id).orElse(null);
        if (produto == null) return null;
        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
        Produto updated = produtoService.save(produto);
        return toDto(updated);
    }

    private ProdutoDto toDto(Produto produto) {
        if (produto == null) return null;
        ProdutoDto dto = new ProdutoDto();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setPreco(produto.getPreco());
        dto.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        return dto;
    }

    private Produto toEntity(ProdutoDto dto) {
        if (dto == null) return null;
        Produto produto = new Produto();
        produto.setId(dto.getId());
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setQuantidadeEstoque(dto.getQuantidadeEstoque());
        return produto;
    }
}
