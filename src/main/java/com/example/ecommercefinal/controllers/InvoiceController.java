package com.example.ecommercefinal.controllers;

import com.example.ecommercefinal.entities.Invoice;
import com.example.ecommercefinal.services.InvoiceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceServices invoiceServices;

    @PostMapping
    public ResponseEntity<Invoice> generateInvoice (@RequestParam Integer client_id)
    {
        try{
            Invoice invoice = invoiceServices.generateInvoice(client_id);
            return ResponseEntity.ok(invoice);
        }catch (RuntimeException e)
        {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{cltId}")
    public ResponseEntity<Invoice> getLastInvoiceByClient (@PathVariable Integer client_id)
    {
        try{
            Invoice invoice = invoiceServices.getLastInvoiceByClient(client_id);
            return ResponseEntity.ok(invoice);
        }
        catch (RuntimeException e)
        {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
