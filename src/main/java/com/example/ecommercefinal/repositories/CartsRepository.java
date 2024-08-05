package com.example.ecommercefinal.repositories;

import com.example.ecommercefinal.entities.Carts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartsRepository extends JpaRepository<Carts, Integer> {

    List<Carts> findByClientIdAndDeliveredFalse (Integer client_Id);
}
