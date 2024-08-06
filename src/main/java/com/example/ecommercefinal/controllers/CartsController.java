package com.example.ecommercefinal.controllers;

import com.example.ecommercefinal.entities.Carts;
import com.example.ecommercefinal.services.CartsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/carts")

public class CartsController {

    @Autowired
    private CartsServices cartsServices;

    @DeleteMapping("/{cltId}")
    public ResponseEntity<Void> deleteCart(@PathVariable Integer cltId)
    {
        try {
            Optional<Carts> cart = cartsServices.deleteCart(cltId);
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
