package com.finalproject.facilgest.entity;


public class Funcionario {

  private String cpf;
  private String nome;
  private String dataDeInicio;
  private String dataDeNascimento;


  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }


  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }


  public String getDataDeInicio() {
    return dataDeInicio;
  }

  public void setDataDeInicio(String dataDeInicio) {
    this.dataDeInicio = dataDeInicio;
  }


  public String getDataDeNascimento() {
    return dataDeNascimento;
  }

  public void setDataDeNascimento(String dataDeNascimento) {
    this.dataDeNascimento = dataDeNascimento;
  }

}
