package com.finalproject.facilgest.controller;

import com.finalproject.facilgest.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {
    @Autowired
    private ClientesRepository clientesRepository;

    @GetMapping
    public String getTest(){
        return clientesRepository.findByCpfCnpj("06514039050").get().toString();
    }
}
