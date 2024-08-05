package com.example.ecommercefinal.controllers;

import com.example.ecommercefinal.entities.Carts;
import com.example.ecommercefinal.services.CartsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/carts")

public class CartsController {

    @Autowired
    private CartsServices cartsServices;


    @PostMapping
    public ResponseEntity<Carts> addProductToCart(@PathVariable Integer cltId, @PathVariable Integer proId, @PathVariable Integer amount)
    {
        try {
            Carts cart = cartsServices.addProductToCart(cltId, proId, amount);
            return ResponseEntity.ok(cart);
        }
        catch (RuntimeException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
