package com.finalproject.facilgest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Produtos {

  private long multiplicadorDesconto;
  private long idProduto;
  private String nome;
  private long valorUn;
  private long estoque;
}
