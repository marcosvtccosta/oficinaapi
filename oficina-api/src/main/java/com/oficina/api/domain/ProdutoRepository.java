package com.oficina.api.domain;

public interface ProdutoRepository {
    Produto findById(Long id);
    Produto save(Produto produto);
    // outros métodos conforme necessidade
}

