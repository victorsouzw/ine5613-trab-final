package com.finalproject.facilgest.service;

import com.finalproject.facilgest.entity.ComprasMes;
import com.finalproject.facilgest.entity.VendasMes;
import com.finalproject.facilgest.repository.ComprasMesRepository;
import com.finalproject.facilgest.repository.VendasMesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendasMesService {
    @Autowired
    VendasMesRepository vendasMesRepository;


    public List<VendasMes> findAll(){
        List<VendasMes> listAllVendasMes = vendasMesRepository.findAll();
        return listAllVendasMes;
    }

    public VendasMes findByCpfAndData(String cpf, String data){
        return vendasMesRepository.findByCpfAndData(cpf, data).get();
    }


    public void deleteByCpfAndData(String cpf, String data){
        vendasMesRepository.deleteByCpfAndData(cpf, data);
    }

    public void insert(VendasMes newVendasMes){


        vendasMesRepository.insert(newVendasMes);
    }

    public void update(VendasMes newVendasMes){

        vendasMesRepository.update(newVendasMes);
    }
}
