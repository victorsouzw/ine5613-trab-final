package com.finalproject.facilgest.controller;

import com.finalproject.facilgest.entity.Clientes;
import com.finalproject.facilgest.service.ClientesService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/clientes")
public class ClientesController {
    @Autowired
    private ClientesService clientesService;
    Gson gson = new Gson();

    @GetMapping("/findAll")
    public String findAll(){

        return gson.toJson(clientesService.findAll());
    }

    @GetMapping("/findByCpfCnpj/{cpfCnpj}")
    public String findByIdTest(@PathVariable String cpfCnpj){
        Clientes cliente = clientesService.findByIdTest(cpfCnpj);
        return gson.toJson(cliente);
    }

    @PutMapping("/deleteByCpfCnpj/{cpfCnpj}")
    public void deleteByCpfCnpj(@PathVariable String cpfCnpj){
        clientesService.deleteByCpfCnpj(cpfCnpj);
    }
    @PostMapping("/insert")
    public void insert(@RequestBody String newClienteJson){

        Clientes newCliente = gson.fromJson(newClienteJson, Clientes.class);
        clientesService.insert(newCliente);
    }

    @PostMapping("/update")
    public void update(@RequestBody String newClienteJson){

        Clientes newCliente = gson.fromJson(newClienteJson, Clientes.class);
        clientesService.update(newCliente);
    }
}
