package com.oficina.api.domain;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "OFICINAAPI", name = "CLIENTE")
public class Cliente {
    private Long id;
    private String nome;
    private String cpfOuCnpj;
}
