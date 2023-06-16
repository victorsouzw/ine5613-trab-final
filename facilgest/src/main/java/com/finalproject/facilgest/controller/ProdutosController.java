package com.finalproject.facilgest.controller;

import com.finalproject.facilgest.entity.Clientes;
import com.finalproject.facilgest.entity.Produtos;
import com.finalproject.facilgest.service.ClientesService;
import com.finalproject.facilgest.service.ProdutosService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("")
public class ProdutosController {
    @Autowired
    private ProdutosService produtosService;
    Gson gson = new Gson();

    @GetMapping("/produtos/findAll")
    public String findAll(){
        return gson.toJson(produtosService.findAll());
    }

    @GetMapping("/produtos/findById/{id}")
    public String findByIdTest(@PathVariable int id){
        try{
            Produtos produtos = produtosService.findByIdTest(id);
            return gson.toJson(produtos);
        }catch (Exception e){
            return e.toString();
        }
    }

    @PutMapping("/produtos/deleteById/{id}")
    public String deleteByCpfCnpj(@PathVariable int id){

        try{
            produtosService.deleteById(id);
            return "delete realizado com sucesso!";
        } catch (Exception e) {
            return e.toString();
        }
    }
    @PostMapping("/produtos/insert")
    public String insert(@RequestBody String newProdutoJson){
        try{
            Produtos newProduto = gson.fromJson(newProdutoJson, Produtos.class);
            produtosService.insert(newProduto);
            return "insert realizado com sucesso!";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @PostMapping("/produtos/update")
    public String update(@RequestBody String newProdutoJson){
        try{
            Produtos newProduto = gson.fromJson(newProdutoJson, Produtos.class);
            produtosService.update(newProduto);
            return "update realizado com sucesso!";
        } catch (Exception e) {
            return e.toString();
        }
    }
}
