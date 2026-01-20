package com.oficina.api.web.controller;

import com.oficina.api.domain.Produto;
import com.oficina.api.domain.ProdutoRepository;
import com.oficina.api.web.dto.ProdutoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/{id}")
    public ProdutoDto getProduto(@PathVariable Long id) {
        Produto produto = produtoRepository.findById(id);
        return toDto(produto);
    }

    @PostMapping
    public ProdutoDto createProduto(@RequestBody ProdutoDto produtoDto) {
        Produto produto = toEntity(produtoDto);
        Produto saved = produtoRepository.save(produto);
        return toDto(saved);
    }

    @PutMapping("/{id}")
    public ProdutoDto updateProduto(@PathVariable Long id, @RequestBody ProdutoDto produtoDto) {
        Produto produto = toEntity(produtoDto);
        produto.setId(id);
        Produto updated = produtoRepository.save(produto);
        return toDto(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteProduto(@PathVariable Long id) {
        // Implementação de remoção
    }

    @PostMapping("/{id}/adicionar-estoque")
    public ProdutoDto adicionarEstoque(@PathVariable Long id, @RequestParam int quantidade) {
        Produto produto = produtoRepository.findById(id);
        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + quantidade);
        Produto updated = produtoRepository.save(produto);
        return toDto(updated);
    }

    @PostMapping("/{id}/remover-estoque")
    public ProdutoDto removerEstoque(@PathVariable Long id, @RequestParam int quantidade) {
        Produto produto = produtoRepository.findById(id);
        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
        Produto updated = produtoRepository.save(produto);
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
