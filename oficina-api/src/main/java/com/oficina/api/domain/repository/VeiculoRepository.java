package com.oficina.api.domain.repository;

import com.oficina.api.domain.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, String> {
    Veiculo findByPlaca(String placa);
    // outros métodos personalizados, se necessário
}
