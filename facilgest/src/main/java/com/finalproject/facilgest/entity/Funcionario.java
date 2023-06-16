package com.finalproject.facilgest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Funcionario {

  private String cpf;
  private String nome;
  private String dataDeInicio;
  private String dataDeNascimento;

}
