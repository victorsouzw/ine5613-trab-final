package com.finalproject.facilgest.service;

import com.finalproject.facilgest.entity.Gerente;
import com.finalproject.facilgest.entity.Vendedor;
import com.finalproject.facilgest.repository.GerenteRepository;
import com.finalproject.facilgest.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendedorService {
    @Autowired
    VendedorRepository vendedorRepository;


    public List<Vendedor> findAll(){
        List<Vendedor> listAllVendedor = vendedorRepository.findAll();
        return listAllVendedor;
    }

    public Vendedor findByIdTest(String cpf){
        return vendedorRepository.findByCpf(cpf).get();
    }


    public void deleteByCpf(String cpf){

            vendedorRepository.deleteByCpf(cpf);
    }

    public void insert(Vendedor newVendedor){


        vendedorRepository.insert(newVendedor);
    }

}
