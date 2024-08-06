package com.example.ecommercefinal.services;

import com.example.ecommercefinal.entities.Carts;
import com.example.ecommercefinal.entities.Clients;
import com.example.ecommercefinal.entities.Invoice;
import com.example.ecommercefinal.repositories.CartsRepository;
import com.example.ecommercefinal.repositories.ClientsRepository;
import com.example.ecommercefinal.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServices {

    @Autowired
    private ClientsRepository clientsRepository;
    @Autowired
    private CartsRepository cartsRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;

    public Invoice generateInvoice (Integer client_Id)
    {
        Optional<Clients> clients = clientsRepository.findById(client_Id);
        if(clients.isPresent())
        {
            List<Carts> carts = cartsRepository.findByClientIdAndDeliveredFalse(client_Id);
            if(carts.isEmpty()){
                throw new RuntimeException("No existen productos en el carrito para el Cliente consultado");
            }
            else {
                Clients clients1 = clients.get();
                Invoice invoice =  new Invoice();
                invoice.setClient(clients1);
                invoice.setCreatedAt(new Date());
                double total = 0.0;
                for (Carts carts1 : carts){
                    total += carts1.getAmount()* carts1.getPrice();
                    carts1.setDelivered(true);
                }
                invoice.setTotal(total);
                return invoiceRepository.save(invoice);
            }
        }else
            throw  new RuntimeException("Cliente no encontrado");
    }

    public Invoice getLastInvoiceByClient(Integer client_id) {
        Optional<Clients> clients = clientsRepository.findById(client_id);
        if (clients.isPresent()) {
            List<Invoice> invoices = clients.get().getInvoice();
            if (invoices != null && !invoices.isEmpty()) {
                return invoices.get(invoices.size() - 1);
            } else {
                throw new RuntimeException("No hay facturas para este cliente");
            }
        } else {
            throw new RuntimeException("Cliente no encontrado");
        }
    }
}

