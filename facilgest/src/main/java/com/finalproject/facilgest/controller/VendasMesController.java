package com.finalproject.facilgest.controller;

import com.finalproject.facilgest.entity.ComprasMes;
import com.finalproject.facilgest.entity.VendasMes;
import com.finalproject.facilgest.service.ComprasMesService;
import com.finalproject.facilgest.service.VendasMesService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("")
public class VendasMesController {
    @Autowired
    private VendasMesService vendasMesService;
    Gson gson = new Gson();

    @GetMapping("/vendasMes/findAll")
    public String findAll(){

        return gson.toJson(vendasMesService.findAll());
    }

    @GetMapping("/vendasMes/findByCpfAndData/{cpf}/{data}")
    public String findByIdTest(@PathVariable String cpf, @PathVariable String data){
        try{
            VendasMes vendasMes = vendasMesService.findByCpfAndData(cpf, data);
            return gson.toJson(vendasMes);
        }catch(Exception e){
            return e.toString();
        }

    }

    @PutMapping("/vendasMes/deleteByCpfAndData/{cpf}/{data}")
    public String deleteByCpfAndData(@PathVariable String cpf, @PathVariable String data){
        try{
            vendasMesService.deleteByCpfAndData(cpf, data);
            return "delete realizado com sucesso";
        }catch(Exception e){
            return e.toString();
        }

    }
    @PostMapping("/vendasMes/insert")
    public String insert(@RequestBody String newVendasMesJson){
        try{
            VendasMes newVendasMes = gson.fromJson(newVendasMesJson, VendasMes.class);
            vendasMesService.insert(newVendasMes);
            return "insert realizado com sucesso";
        }catch(Exception e){
            return e.toString();
        }

    }

    @PostMapping("/vendasMes/update")
    public String update(@RequestBody String newVendasMesJson){
        try{
            VendasMes newVendasMes = gson.fromJson(newVendasMesJson, VendasMes.class);
            vendasMesService.update(newVendasMes);
            return "update realizado com sucesso";
        }catch(Exception e){
            return e.toString();
        }

    }
}
