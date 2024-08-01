package com.example.ecommercefinal.repositories;

import com.example.ecommercefinal.entities.Carts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceDetailsRepository extends JpaRepository<Carts, Integer> {
}
