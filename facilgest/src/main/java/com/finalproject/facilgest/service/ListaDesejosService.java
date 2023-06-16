package com.finalproject.facilgest.service;

import com.finalproject.facilgest.entity.ComprasMes;
import com.finalproject.facilgest.entity.ListaDesejos;
import com.finalproject.facilgest.repository.ComprasMesRepository;
import com.finalproject.facilgest.repository.ListaDesejosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaDesejosService {
    @Autowired
    ListaDesejosRepository listaDesejosRepository;


    public List<ListaDesejos> findAll(){
        List<ListaDesejos> listAllListaDesejos = listaDesejosRepository.findAll();
        return listAllListaDesejos;
    }

    public ListaDesejos findByCpfAndData(String cpf, int id){
        return listaDesejosRepository.findByCpfAndId(cpf, id).get();
    }


    public void deleteByCpfAndData(String cpf, int id){
        listaDesejosRepository.deleteByCpfAndId(cpf, id);
    }

    public void insert(ListaDesejos newListaDesejos){


        listaDesejosRepository.insert(newListaDesejos);
    }

    public void update(ListaDesejos newListaDesejos){

        listaDesejosRepository.update(newListaDesejos);
    }
}
