package com.finalproject.facilgest.consultasAgregacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EntityQueryTwo {
    private String cpf;
    private String nome;
    private String dataDeInicio;
    private int total_vendido;
}
