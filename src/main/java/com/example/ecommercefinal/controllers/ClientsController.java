package com.example.ecommercefinal.controllers;

import com.example.ecommercefinal.entities.Clients;
import com.example.ecommercefinal.services.ClientsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/auth/register")
public class ClientsController {

    @Autowired
    private ClientsServices clientsServices;

    @PostMapping()
    public ResponseEntity<Clients> saveClient(@RequestBody Clients data) {
        try {
            Clients clients = clientsServices.saveClient(data);
            return new ResponseEntity<>(clients, HttpStatus.CREATED);
        } catch (Exception e)
        {
            System.out.println("Error de registro");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clients> readClients(@PathVariable Integer id) {
        try {
            Optional<Clients> clients = clientsServices.readOne(id);
            if (clients.isPresent()) {
                return ResponseEntity.ok(clients.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Clients>> readAll() {
        try {
            List<Clients> users = clientsServices.readAll();
            if (!users.isEmpty()) {
                return ResponseEntity.ok(users);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PatchMapping("/api/v1/auth/me/{id}")
    public ResponseEntity<Clients> updateUser(@PathVariable Integer id, @RequestBody Clients data) {
        try {
            Optional<Clients> Clients = clientsServices.readOne(id);
            if (Clients.isPresent()) {
                Clients clients = Clients.get();
                clients.setName(data.getName());
                clients.setLastName(data.getLastName());
                clients.setDocument(data.getDocument());
                return ResponseEntity.ok(clientsServices.saveClient(clients));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Clients> delete(@PathVariable Integer id) {
        try {
            Optional<Clients> optionalClients = clientsServices.readOne(id);
            if (optionalClients.isPresent()) {
                clientsServices.destroyOneClients(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
