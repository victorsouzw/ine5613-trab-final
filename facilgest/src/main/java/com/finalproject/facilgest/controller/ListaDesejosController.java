package com.finalproject.facilgest.controller;

import com.finalproject.facilgest.entity.ComprasMes;
import com.finalproject.facilgest.entity.ListaDesejos;
import com.finalproject.facilgest.service.ComprasMesService;
import com.finalproject.facilgest.service.ListaDesejosService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("")
public class ListaDesejosController {
    @Autowired
    private ListaDesejosService listaDesejosService;
    Gson gson = new Gson();

    @GetMapping("/listaDesejos/findAll")
    public String findAll(){

        return gson.toJson(listaDesejosService.findAll());
    }

    @GetMapping("/listaDesejos/findByCpfAndId/{cpf}/{id}")
    public String findByIdTest(@PathVariable String cpf, @PathVariable int id){
        try{
            ListaDesejos listaDesejos = listaDesejosService.findByCpfAndData(cpf, id);
            return gson.toJson(listaDesejos);
        } catch (Exception e) {
            return e.toString();
        }

    }

    @PutMapping("/listaDesejos/deleteByCpfAndId/{cpf}/{id}")
    public String deleteByCpfAndData(@PathVariable String cpf, @PathVariable int id){
        try{
            listaDesejosService.deleteByCpfAndData(cpf, id);
            return "item da lista de desejo deletado com sucesso";
        } catch (Exception e) {
            return e.toString();
        }
    }
    @PostMapping("/listaDesejos/insert")
    public String insert(@RequestBody String newListaDesejosJson){
        try{
            ListaDesejos newListaDesejos = gson.fromJson(newListaDesejosJson, ListaDesejos.class);
            listaDesejosService.insert(newListaDesejos);
            return "item da lista de desejo inserido com sucesso";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @PostMapping("/listaDesejos/update")
    public String update(@RequestBody String newListaDesejosJson){
        try{
            ListaDesejos newListaDesejos = gson.fromJson(newListaDesejosJson, ListaDesejos.class);
            listaDesejosService.update(newListaDesejos);
            return "item da lista de desejo atualizado com sucesso";
        } catch (Exception e) {
            return e.toString();
        }
    }
}
