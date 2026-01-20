package com.oficina.api.domain;

import lombok.*;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "OFICINAAPI", name = "SERVICO")
public class Servico {
    private Long id;
    private String nome;
    private BigDecimal preco;
}
