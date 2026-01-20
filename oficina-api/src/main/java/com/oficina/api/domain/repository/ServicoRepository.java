package com.oficina.api.domain.repository;

import com.oficina.api.domain.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
    // outros métodos personalizados, se necessário
}
