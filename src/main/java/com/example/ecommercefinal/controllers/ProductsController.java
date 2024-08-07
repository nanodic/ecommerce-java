package com.example.ecommercefinal.controllers;

import com.example.ecommercefinal.entities.Products;
import com.example.ecommercefinal.services.ProductsServices;
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
@RequestMapping (path="api/v1/Products")
@Tag(name = "ProductsController", description = "CRUD Productos")
public class ProductsController {

    @Autowired
    private ProductsServices productsServices;

    @PostMapping()
    @Operation(summary = "Generate Producto", description = "Registra un nuevo producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto registrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",content = @Content),
    })
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
    @Operation(summary = "Find Productos", description = "Lista todos los productos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve los productos registrados"),
            @ApiResponse(responseCode = "404", description = "No devuelve datos", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",content = @Content),
    })
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
    @Operation(summary = "Find Producto por Id", description = "Busca y muestra un producto especifico por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve al carrito buscado"),
            @ApiResponse(responseCode = "404", description = "No devuelve datos, no encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",content = @Content),
    })
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "203", description = "Elimina el producto, no devuelve datos"),
            @ApiResponse(responseCode = "404", description = "No devuelve datos", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",content = @Content),
    })
    @Operation(summary = "Delete Producto", description = "Elimina un producto")
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
    @Operation(summary = "Update Producto", description = "Actualiza un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado"),
            @ApiResponse(responseCode = "404", description = "No devuelve datos", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",content = @Content),
    })
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
