package com.finalproject.facilgest.service;

import com.finalproject.facilgest.entity.Clientes;
import com.finalproject.facilgest.entity.Produtos;
import com.finalproject.facilgest.repository.ClientesRepository;
import com.finalproject.facilgest.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutosService {
    @Autowired
    ProdutosRepository produtosRepository;


    public List<Produtos> findAll(){
        List<Produtos> listAllProdutos = produtosRepository.findAll();
        return listAllProdutos;
    }

    public Produtos findByIdTest(int id){
        return produtosRepository.findById(id).get();
    }


    public void deleteById(int id){
        produtosRepository.deleteById(id);
    }

    public void insert(Produtos newProdutos){


        produtosRepository.insert(newProdutos);
    }

    public void update(Produtos newProdutos){

        produtosRepository.update(newProdutos);
    }
}
