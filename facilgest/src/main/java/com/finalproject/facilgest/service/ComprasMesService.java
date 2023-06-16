package com.finalproject.facilgest.service;

import com.finalproject.facilgest.entity.Clientes;
import com.finalproject.facilgest.entity.ComprasMes;
import com.finalproject.facilgest.repository.ClientesRepository;
import com.finalproject.facilgest.repository.ComprasMesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComprasMesService {
    @Autowired
    ComprasMesRepository comprasMesRepository;


    public List<ComprasMes> findAll(){
        List<ComprasMes> listAllComprasMes = comprasMesRepository.findAll();
        return listAllComprasMes;
    }

    public ComprasMes findByCpfAndData(String cpf, String data){
        return comprasMesRepository.findByCpfAndData(cpf, data).get();
    }


    public void deleteByCpfAndData(String cpf, String data){
        comprasMesRepository.deleteByCpfAndData(cpf, data);
    }

    public void insert(ComprasMes newComprasMes){


        comprasMesRepository.insert(newComprasMes);
    }

    public void update(ComprasMes newComprasMes){

        comprasMesRepository.update(newComprasMes);
    }
}
