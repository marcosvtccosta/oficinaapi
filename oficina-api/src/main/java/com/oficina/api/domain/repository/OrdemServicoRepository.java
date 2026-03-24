package com.oficina.api.domain.repository;

import com.oficina.api.domain.entity.OrdemServico;
import com.oficina.api.domain.entity.OrdemServicoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
    List<OrdemServico> findByStatusNotIn(List<OrdemServicoStatus> status);
}
