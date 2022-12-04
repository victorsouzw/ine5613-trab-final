package com.finalproject.facilgest.entity;


public class Fornecedor {

  private String cnpj;
  private String nome;
  private String tipoDeProduto;
  private long idNota;


  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }


  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }


  public String getTipoDeProduto() {
    return tipoDeProduto;
  }

  public void setTipoDeProduto(String tipoDeProduto) {
    this.tipoDeProduto = tipoDeProduto;
  }


  public long getIdNota() {
    return idNota;
  }

  public void setIdNota(long idNota) {
    this.idNota = idNota;
  }

}
