package com.finalproject.facilgest.controller;

import com.finalproject.facilgest.entity.Clientes;
import com.finalproject.facilgest.entity.Funcionario;
import com.finalproject.facilgest.service.ClientesService;
import com.finalproject.facilgest.service.FuncionarioService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController("")
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;
    Gson gson = new Gson();

    @GetMapping("/funcionario/findAll")
    public String findAll(){

        return gson.toJson(funcionarioService.findAll());
    }

    @GetMapping("/funcionario/findByCpf/{cpf}")
    public String findByIdTest(@PathVariable String cpf){
        Funcionario funcionario = funcionarioService.findByIdTest(cpf);
        return gson.toJson(funcionario);
    }

    @PutMapping("/funcionario/deleteByCpf/{cpf}")
    public String deleteByCpfCnpj(@PathVariable String cpf){
        try{
            funcionarioService.deleteByCpf(cpf);
            return "funcionario deletado";
        }catch (Exception e) {
            return e.toString();
        }

    }
    @PostMapping("/funcionario/insert")
    public void insert(@RequestBody String newFuncionarioJson){

        Funcionario newFuncionario = gson.fromJson(newFuncionarioJson, Funcionario.class);
        funcionarioService.insert(newFuncionario);
    }

    @PostMapping("/funcionario/update")
    public void update(@RequestBody String newFuncionarioJson){

        Funcionario newFuncionario = gson.fromJson(newFuncionarioJson, Funcionario.class);
        funcionarioService.update(newFuncionario);
    }
}
