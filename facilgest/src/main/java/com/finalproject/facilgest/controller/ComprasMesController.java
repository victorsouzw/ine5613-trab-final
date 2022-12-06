package com.finalproject.facilgest.controller;

import com.finalproject.facilgest.entity.Clientes;
import com.finalproject.facilgest.entity.ComprasMes;
import com.finalproject.facilgest.service.ClientesService;
import com.finalproject.facilgest.service.ComprasMesService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("")
public class ComprasMesController {
    @Autowired
    private ComprasMesService comprasMesService;
    Gson gson = new Gson();

    @GetMapping("/comprasMes/findAll")
    public String findAll(){

        return gson.toJson(comprasMesService.findAll());
    }

    @GetMapping("/comprasMes/findByCpfAndData/{cpf}/{data}")
    public String findByIdTest(@PathVariable String cpf, @PathVariable String data){
        ComprasMes comprasMes = comprasMesService.findByCpfAndData(cpf, data);
        return gson.toJson(comprasMes);
    }

    @PutMapping("/comprasMes/deleteByCpfAndData/{cpf}/{data}")
    public void deleteByCpfAndData(@PathVariable String cpf, @PathVariable String data){
        comprasMesService.deleteByCpfAndData(cpf, data);
    }
    @PostMapping("/comprasMes/insert")
    public void insert(@RequestBody String newComprasMesJson){

        ComprasMes newComprasMes = gson.fromJson(newComprasMesJson, ComprasMes.class);
        comprasMesService.insert(newComprasMes);
    }

    @PostMapping("/comprasMes/update")
    public void update(@RequestBody String newComprasMesJson){

        ComprasMes newComprasMes = gson.fromJson(newComprasMesJson, ComprasMes.class);
        comprasMesService.update(newComprasMes);
    }
}
