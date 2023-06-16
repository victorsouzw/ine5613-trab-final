package com.finalproject.facilgest.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotaFiscal {

  private long idNota;
  private String tipo;
  private String data;
  private long totalNota;
  private String cpfCnpj;
  private String cpfVendedor;
  private String cpfGerente;
  private String cnpjFornecedor;
}
