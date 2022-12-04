package com.finalproject.facilgest.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fornecedor {

  private String cnpj;
  private String nome;
  private String tipoDeProduto;
  private long idNota;
}
