package com.finalproject.facilgest.service;

import com.finalproject.facilgest.entity.Funcionario;
import com.finalproject.facilgest.entity.Gerente;
import com.finalproject.facilgest.repository.FuncionarioRepository;
import com.finalproject.facilgest.repository.GerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GerenteService {
    @Autowired
    GerenteRepository gerenteRepository;


    public List<Gerente> findAll(){
        List<Gerente> listAllGerente = gerenteRepository.findAll();
        return listAllGerente;
    }

    public Gerente findByIdTest(String cpf){
        return gerenteRepository.findByCpf(cpf).get();
    }


    public void deleteByCpf(String cpf){

            gerenteRepository.deleteByCpf(cpf);
    }

    public void insert(Gerente newGerente){


        gerenteRepository.insert(newGerente);
    }

}
