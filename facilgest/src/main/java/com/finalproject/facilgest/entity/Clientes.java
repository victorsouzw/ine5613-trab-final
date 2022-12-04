package com.finalproject.facilgest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Clientes {

  private String endereco;
  private String cpfCnpj;
  private String dataDeNascimento;
  private String email;
  private String nomeCompleto;
  private String telefone;
  private long idNota;
}
