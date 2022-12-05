package com.finalproject.facilgest.service;

import com.finalproject.facilgest.entity.Clientes;
import com.finalproject.facilgest.entity.Funcionario;
import com.finalproject.facilgest.repository.ClientesRepository;
import com.finalproject.facilgest.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {
    @Autowired
    FuncionarioRepository funcionarioRepository;


    public List<Funcionario> findAll(){
        List<Funcionario> listAllFuncionario = funcionarioRepository.findAll();
        return listAllFuncionario;
    }

    public Funcionario findByIdTest(String cpf){
        return funcionarioRepository.findByCpf(cpf).get();
    }


    public void deleteByCpf(String cpf){

            funcionarioRepository.deleteByCpf(cpf);
    }

    public void insert(Funcionario newFuncionario){


        funcionarioRepository.insert(newFuncionario);
    }

    public void update(Funcionario newFuncionario){

        funcionarioRepository.update(newFuncionario);
    }
}
