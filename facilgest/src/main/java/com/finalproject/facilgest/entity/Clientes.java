package com.finalproject.facilgest.entity;

import lombok.*;

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
}
