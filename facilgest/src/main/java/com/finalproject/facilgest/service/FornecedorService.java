package com.finalproject.facilgest.service;

import com.finalproject.facilgest.entity.Clientes;
import com.finalproject.facilgest.entity.Fornecedor;
import com.finalproject.facilgest.repository.FornecedoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {
    @Autowired
    FornecedoresRepository fornecedoresRepository;


    public List<Fornecedor> findAll(){
        List<Fornecedor> listAllFornecedores = fornecedoresRepository.findAll();
        return listAllFornecedores;
    }

    public Fornecedor findByCnpj(String cnpj){
        return fornecedoresRepository.findByCnpj(cnpj).get();
    }


    public void deleteByCnpj(String cnpj){
        fornecedoresRepository.deleteByCnpj(cnpj);
    }

    public void insert(Fornecedor newFornecedor){


        fornecedoresRepository.insert(newFornecedor);
    }

    public void update(Fornecedor newFornecedor){

        fornecedoresRepository.update(newFornecedor);
    }
}
