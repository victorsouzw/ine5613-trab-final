package com.finalproject.facilgest.controller;

import com.finalproject.facilgest.entity.Clientes;
import com.finalproject.facilgest.entity.Fornecedor;
import com.finalproject.facilgest.service.ClientesService;
import com.finalproject.facilgest.service.FornecedorService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("")
public class FornecedorController {
    @Autowired
    private FornecedorService fornecedorService;
    Gson gson = new Gson();

    @GetMapping("/fornecedores/findAll")
    public String findAll(){

        return gson.toJson(fornecedorService.findAll());
    }

    @GetMapping("/fornecedores/findByCnpj/{cnpj}")
    public String findByIdTest(@PathVariable String cnpj){
        Fornecedor fornecedor = fornecedorService.findByCnpj(cnpj);
        return gson.toJson(fornecedor);
    }

    @PutMapping("/fornecedores/deleteByCnpj/{cnpj}")
    public void deleteByCpfCnpj(@PathVariable String cnpj){
        fornecedorService.deleteByCnpj(cnpj);
    }
    @PostMapping("/fornecedores/insert")
    public void insert(@RequestBody String newFornecedorJson){

        Fornecedor newFornecedor = gson.fromJson(newFornecedorJson, Fornecedor.class);
        fornecedorService.insert(newFornecedor);
    }

    @PostMapping("/fornecedores/update")
    public void update(@RequestBody String newFornecedorJson){

        Fornecedor newFornecedor = gson.fromJson(newFornecedorJson, Fornecedor.class);
        fornecedorService.update(newFornecedor);
    }
}
