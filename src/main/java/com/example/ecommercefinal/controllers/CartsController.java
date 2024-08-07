package com.example.ecommercefinal.controllers;

import com.example.ecommercefinal.entities.Carts;
import com.example.ecommercefinal.services.CartsServices;
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
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/carts")
@Tag(name = "CartsController", description = "CRUD Carrito")
public class CartsController {

    @Autowired
    private CartsServices cartsServices;

    @DeleteMapping("/{cartId}")
    @Operation(summary = "Delete Cart / carrito", description = "Permite eliminar un carrito que aun no fue facturado, eliminando el registro para el usuario asociado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Elinado correctamente, no devuelve datos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado, no devuelve datos", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",content = @Content),
    })
    public ResponseEntity<Void> deleteCart(@PathVariable Integer cartId)
    {
        try {
            cartsServices.removeProductsFromCarts(cartId);
            return ResponseEntity.noContent().build();
        }
        catch (RuntimeException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch (Exception Ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/{cltId}/{proId}/{amount}")
    @Operation(summary = "Generate Cart / carrito", description = "Permite generar un carrito (delivered False), asociado a un Cliente, es necesario pasar el Id del producto y su cantidad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Carrito generado correctamente"),
            @ApiResponse(responseCode = "404", description = "No devuelve datos", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",content = @Content),
    })
    public ResponseEntity<Carts> addProductToCart(@PathVariable Integer cltId, @PathVariable Integer proId, @PathVariable Integer amount)
    {
        try {
            Carts cart = cartsServices.addProductToCart(cltId, proId, amount);
            return ResponseEntity.status(HttpStatus.CREATED).body(cart);
        }
        catch (RuntimeException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @GetMapping("/{cltId}")
    @Operation(summary = "Find Cart / carrito", description = "Permite buscar carritos que aun no fueron facturados pasando el ID del Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve al cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "No devuelve datos, carrito no encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",content = @Content),
    })
    public ResponseEntity<List<Carts>> findCartsByClientAndDelivered(@PathVariable Integer cltId)
    {
        try{
            List<Carts> carts = cartsServices.findByClientIdAndDeliveredFalse(cltId);
            return ResponseEntity.ok(carts);
        }
        catch (RuntimeException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch (Exception Ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
