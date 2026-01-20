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
@Table(schema = "OFICINAAPI", name = "PRODUTO")
public class Produto {
    private Long id;
    private String nome;
    private BigDecimal preco;
    private Integer quantidadeEstoque;
}
