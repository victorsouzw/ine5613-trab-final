package com.finalproject.facilgest.consultasAgregacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EntityQueryOne {
    private Long idProduto;
    private String nome;
    private int total;
}
