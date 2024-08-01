package com.example.ecommercefinal.controllers;

import com.example.ecommercefinal.entities.Products;
import com.example.ecommercefinal.services.ProductsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping (path="api/v1/Products")
public class ProductsController {

    @Autowired
    private ProductsServices productsServices;

    @PostMapping()
    public ResponseEntity<Products>  saveProducts(@RequestBody Products data) {
        try {
            Products products = productsServices.saveProducts(data);
            return new ResponseEntity<>(products, HttpStatus.CREATED);
        } catch (Exception e)
        {
            System.out.println("Error de registro");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<Products>> readProducts() {
        try {
            List<Products> products = productsServices.readProducts();
            if (!products.isEmpty()) {
                return ResponseEntity.ok(products);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> readOne(@PathVariable("id") Integer  id) {
        try {
            Optional<Products> products = productsServices.readOne(id);
            if (products.isPresent()) {
                return ResponseEntity.ok(products.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Products> deleteProducts(@PathVariable("id") Integer  id) {
        try {
            Optional<Products> products = productsServices.readOne(id);
            if (products.isPresent()) {
                productsServices.delete(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Products> UpdateProducts(@PathVariable Integer id, @RequestBody Products data) {
        try {
            Optional<Products> Products = productsServices.readOne(id);
            if (Products.isPresent()) {
                Products products = Products.get();
                products.setCode(data.getCode());
                products.setStock(data.getStock());
                products.setPrice(data.getPrice());
                products.setDescription(data.getDescription());
                return ResponseEntity.ok(productsServices.saveProducts(products));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
