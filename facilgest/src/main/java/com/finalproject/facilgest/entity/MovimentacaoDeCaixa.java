package com.finalproject.facilgest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovimentacaoDeCaixa {

  private long idMovimentacao;
  private String tipo;
  private String dataDeRealizacao;
  private long valor;
  private long idNota;
}
