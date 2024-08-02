package com.example.ecommercefinal.repositories;

import com.example.ecommercefinal.entities.Carts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CartsRepository extends JpaRepository<Carts, Integer> {

    List<Carts> cartsNoDeliveried(Integer client_id, boolean b);
}
