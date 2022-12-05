package com.finalproject.facilgest.controller;

import com.finalproject.facilgest.entity.Funcionario;
import com.finalproject.facilgest.entity.Gerente;
import com.finalproject.facilgest.service.FuncionarioService;
import com.finalproject.facilgest.service.GerenteService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("")
public class GerenteController {
    @Autowired
    private GerenteService gerenteService;
    Gson gson = new Gson();

    @GetMapping("/gerente/findAll")
    public String findAll(){

        return gson.toJson(gerenteService.findAll());
    }

    @GetMapping("/gerente/findByCpf/{cpf}")
    public String findByIdTest(@PathVariable String cpf){
        Gerente gerente = gerenteService.findByIdTest(cpf);
        return gson.toJson(gerente);
    }

    @PutMapping("/gerente/deleteByCpf/{cpf}")
    public String deleteByCpfCnpj(@PathVariable String cpf){
        try{
            gerenteService.deleteByCpf(cpf);
            return "funcionario deletado";
        }catch (Exception e) {
            return e.toString();
        }

    }
    @PostMapping("/gerente/insert")
    public String insert(@RequestBody String newGerenteJson){
        try{
            Gerente newGerente = gson.fromJson(newGerenteJson, Gerente.class);
            gerenteService.insert(newGerente);
            return "gerente inserido com sucesso";
        }catch (Exception e) {
            return e.toString();
        }


    }
}
