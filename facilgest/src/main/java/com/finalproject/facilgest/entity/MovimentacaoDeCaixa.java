package com.finalproject.facilgest.entity;


public class MovimentacaoDeCaixa {

  private long idMovimentacao;
  private String tipo;
  private String dataDeRealizacao;
  private long valor;
  private long idNota;


  public long getIdMovimentacao() {
    return idMovimentacao;
  }

  public void setIdMovimentacao(long idMovimentacao) {
    this.idMovimentacao = idMovimentacao;
  }


  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }


  public String getDataDeRealizacao() {
    return dataDeRealizacao;
  }

  public void setDataDeRealizacao(String dataDeRealizacao) {
    this.dataDeRealizacao = dataDeRealizacao;
  }


  public long getValor() {
    return valor;
  }

  public void setValor(long valor) {
    this.valor = valor;
  }


  public long getIdNota() {
    return idNota;
  }

  public void setIdNota(long idNota) {
    this.idNota = idNota;
  }

}
