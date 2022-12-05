package com.finalproject.facilgest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComprasMes {

  private long numCompras;
  private long valorCompras;
  private String dataCompras;
  private String cpfGerente;

}
