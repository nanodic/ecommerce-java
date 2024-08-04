package com.example.ecommercefinal.repositories;

import com.example.ecommercefinal.entities.Carts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartsRepository extends JpaRepository<Carts, Integer> {
}
