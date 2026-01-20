package com.oficina.api.domain.repository;

import com.oficina.api.domain.entity.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
    // Métodos personalizados, se necessário
}
