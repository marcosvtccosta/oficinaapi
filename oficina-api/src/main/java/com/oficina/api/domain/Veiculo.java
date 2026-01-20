package com.oficina.api.domain;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "OFICINAAPI", name = "VEICULO")
public class Veiculo {
    private  Long id;
    private String placa;
    private String marca;
    private String modelo;
    private Integer ano;
}
