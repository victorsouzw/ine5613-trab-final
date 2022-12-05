package com.finalproject.facilgest.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vendedor {

  private long numeroDeVendasMes;
  private long valorVendasMes;
  private String cpfVendedor;
}
