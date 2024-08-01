package com.example.ecommercefinal.repositories;

import com.example.ecommercefinal.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice ,Integer> {
}
