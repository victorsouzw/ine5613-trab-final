package com.finalproject.facilgest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListaDesejos {
  @Column("preço_desejado")
  private long precoDesejado;
  private String cpfCnpj;
  private long idProduto;

}
