package com.finalproject.facilgest.consultasAgregacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EntityQueryThree {
    private String cpfCnpj;
    private String nomeCompleto;
    private int compras;
    private int total;
}
