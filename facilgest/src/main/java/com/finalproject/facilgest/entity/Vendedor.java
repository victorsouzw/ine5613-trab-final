package com.finalproject.facilgest.entity;


public class Vendedor {

  private long numeroDeVendasMes;
  private long valorVendasMes;
  private String cpf;
  private long idNota;


  public long getNumeroDeVendasMes() {
    return numeroDeVendasMes;
  }

  public void setNumeroDeVendasMes(long numeroDeVendasMes) {
    this.numeroDeVendasMes = numeroDeVendasMes;
  }


  public long getValorVendasMes() {
    return valorVendasMes;
  }

  public void setValorVendasMes(long valorVendasMes) {
    this.valorVendasMes = valorVendasMes;
  }


  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }


  public long getIdNota() {
    return idNota;
  }

  public void setIdNota(long idNota) {
    this.idNota = idNota;
  }

}
