package com.finalproject.facilgest.service;

import com.finalproject.facilgest.entity.Clientes;
import com.finalproject.facilgest.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class ClientesService {
    @Autowired
    ClientesRepository clientesRepository;


    public List<Clientes> findAll(){
        List<Clientes> listAllClientes = clientesRepository.findAll();
        return listAllClientes;
    }

    public Clientes findByIdTest(String cpfCnpj){
        return clientesRepository.findByCpfCnpj(cpfCnpj).get();
    }


    public void deleteByCpfCnpj(String cpfCnpj){
        clientesRepository.deleteByCpfCnpj(cpfCnpj);
    }

    public void insert(Clientes newCliente){


        clientesRepository.insert(newCliente);
    }

    public void update(Clientes newCliente){

        clientesRepository.update(newCliente);
    }
}
