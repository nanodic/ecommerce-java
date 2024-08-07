package com.example.ecommercefinal.controllers;

import com.example.ecommercefinal.entities.Clients;
import com.example.ecommercefinal.services.ClientsServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/auth/register")
@Tag(name = "ClientsController", description = "CRUD Clientes")
public class ClientsController {

    @Autowired
    private ClientsServices clientsServices;

    @PostMapping()
    @Operation(summary = "Generate Client", description = "Permite registrar un nuevo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Devuelve al cliente registrado"),
            @ApiResponse(responseCode = "404", description = "No devuelve datos", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",content = @Content),
    })
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
    @Operation(summary = "Find Client", description = "Permite buscar un cliente por su Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve al cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "No devuelve datos", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content),
    })
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
    @Operation(summary = "Find Clients", description = "Permite listar todos los clientes existentes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista todos los clientes"),
            @ApiResponse(responseCode = "404", description = "Clientes inexistentes",content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",content = @Content)
    })
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


    @PatchMapping("/me/{id}")
    @Operation(summary = "Update Client", description = "Permite modificar datos de un cliente especifico por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado"),
            @ApiResponse(responseCode = "404", description = "No devuelve datos",content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",content = @Content),
    })
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
    @Operation(summary = "Delete Client", description = "Elimina a un cliente existente por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente eliminado"),
            @ApiResponse(responseCode = "404", description = "No devuelve datos",content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",content = @Content),
    })
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
