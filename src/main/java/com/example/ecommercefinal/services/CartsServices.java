package com.example.ecommercefinal.services;

import com.example.ecommercefinal.entities.Carts;
import com.example.ecommercefinal.entities.Clients;
import com.example.ecommercefinal.entities.Products;
import com.example.ecommercefinal.repositories.CartsRepository;
import com.example.ecommercefinal.repositories.ClientsRepository;
import com.example.ecommercefinal.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Optional;

public class CartsServices {
    @Autowired
    private CartsRepository cartsRepository;
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private ClientsRepository clientsRepository;

    public Carts addProductToCart(Integer client_Id, Integer productId, Integer amount) {
        Optional<Clients> clients = clientsRepository.findById(client_Id);
        Optional<Products> products = productsRepository.findById(productId);
        if (clients.isPresent() & products.isPresent()) {
            Carts carts = new Carts();
            carts.setClientId(clients.get());
            carts.setProductId(products.get());
            carts.setPrice(products.get().getPrice());
            carts.setAmount(amount);
            carts.setDelivered(false);
            return cartsRepository.save(carts);
        } else {
            throw new RuntimeException("Cliente o productos inexistente");
        }
    }

    public Carts removeProductsFromCarts(Integer cart_id) {
        Optional<Carts> carts = cartsRepository.findById(cart_id);
        if (carts.isPresent()) {
            cartsRepository.deleteById(cart_id);
            return carts.get();
        } else {
            throw new RuntimeException("Carrito no encontrado");
        }
    }

//    public List<Carts> findByClientIdAndDeliveredFalse(Integer client_id)
//    {
//        List<Carts> carts = cartsRepository.findByClientIdAndDeliveredFalse(client_id);
//        if(carts.isEmpty()){
//            throw new RuntimeException("Carrito no encontrado");
//        }else{
//            return carts;
//        }
//    }

}
