package com.example.ecommercefinal.services;

import com.example.ecommercefinal.entities.Clients;
import com.example.ecommercefinal.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientsServices {
    @Autowired
    private ClientsRepository repository;

    public Clients saveClient(Clients clients)
    {
        return repository.save(clients);
    }

    public List<Clients> readAll()
    {
        return repository.findAll();
    }

    public Optional<Clients>readOne(Integer id)
    {
        return repository.findById(id);
    }

    public void destroyOneClients(Integer id)
    {
        repository.deleteById(id);
    }



}
