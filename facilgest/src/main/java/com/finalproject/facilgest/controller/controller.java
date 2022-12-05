package com.finalproject.facilgest.controller;

import com.finalproject.facilgest.entity.Clientes;
import com.finalproject.facilgest.repository.ClientesRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class controller {
    @Autowired
    private ClientesRepository clientesRepository;
    Gson gson = new Gson();
    @GetMapping("/findAll")
    public String findAll(){
        List<Clientes> listAllClientes = clientesRepository.findAll();
        return gson.toJson(listAllClientes);
    }

    @GetMapping("/findByCpfCnpj/{cpfCnpj}")
    public String findByIdTest(@PathVariable int cpfCnpj){
        return clientesRepository.findByCpfCnpj(cpfCnpj).get().toString();
    }

    @PutMapping("/deleteByCpfCnpj/{cpfCnpj}")
    public void deleteByCpfCnpj(@PathVariable int cpfCnpj){
        clientesRepository.deleteByCpfCnpj(cpfCnpj);
    }
    @PostMapping("/insert")
    public void insertTest(@RequestBody String newClienteJson){

        Clientes newCliente = gson.fromJson(newClienteJson, Clientes.class);
        clientesRepository.insert(newCliente);
    }

    @PostMapping("/update")
    public void updateTest(@RequestBody String newClienteJson){

        Clientes newCliente = gson.fromJson(newClienteJson, Clientes.class);
        clientesRepository.update(newCliente);
    }
}
