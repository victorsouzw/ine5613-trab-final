package com.finalproject.facilgest.entity;


public class Produtos {

  private long multiplicadorDesconto;
  private long idProduto;
  private String nome;
  private String valorUn;
  private String estoque;


  public long getMultiplicadorDesconto() {
    return multiplicadorDesconto;
  }

  public void setMultiplicadorDesconto(long multiplicadorDesconto) {
    this.multiplicadorDesconto = multiplicadorDesconto;
  }


  public long getIdProduto() {
    return idProduto;
  }

  public void setIdProduto(long idProduto) {
    this.idProduto = idProduto;
  }


  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }


  public String getValorUn() {
    return valorUn;
  }

  public void setValorUn(String valorUn) {
    this.valorUn = valorUn;
  }


  public String getEstoque() {
    return estoque;
  }

  public void setEstoque(String estoque) {
    this.estoque = estoque;
  }

}
