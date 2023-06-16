package com.finalproject.facilgest.controller;

import com.finalproject.facilgest.entity.Gerente;
import com.finalproject.facilgest.entity.Vendedor;
import com.finalproject.facilgest.service.GerenteService;
import com.finalproject.facilgest.service.VendedorService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("")
public class VendedorController {
    @Autowired
    private VendedorService vendedorService;
    Gson gson = new Gson();

    @GetMapping("/vendedor/findAll")
    public String findAll(){

        return gson.toJson(vendedorService.findAll());
    }

    @GetMapping("/vendedor/findByCpf/{cpf}")
    public String findByIdTest(@PathVariable String cpf){
        Vendedor vendedor = vendedorService.findByIdTest(cpf);
        return gson.toJson(vendedor);
    }

    @PutMapping("/vendedor/deleteByCpf/{cpf}")
    public String deleteByCpfCnpj(@PathVariable String cpf){
        try{
            vendedorService.deleteByCpf(cpf);
            return "vendedor deletado";
        }catch (Exception e) {
            return e.toString();
        }

    }
    @PostMapping("/vendedor/insert")
    public String insert(@RequestBody String newVendedorJson){
        try{
            Vendedor newVendedor = gson.fromJson(newVendedorJson, Vendedor.class);
            vendedorService.insert(newVendedor);
            return "vendedor inserido com sucesso";
        }catch (Exception e) {
            return e.toString();
        }


    }
}
