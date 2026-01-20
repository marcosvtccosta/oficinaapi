package com.oficina.api.domain.repository;

import com.oficina.api.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // outros métodos conforme necessidade
}
