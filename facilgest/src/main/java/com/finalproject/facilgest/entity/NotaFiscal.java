package com.finalproject.facilgest.entity;


public class NotaFiscal {

  private long idNota;
  private String tipo;
  private String data;
  private String totalNota;


  public long getIdNota() {
    return idNota;
  }

  public void setIdNota(long idNota) {
    this.idNota = idNota;
  }


  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }


  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }


  public String getTotalNota() {
    return totalNota;
  }

  public void setTotalNota(String totalNota) {
    this.totalNota = totalNota;
  }

}
