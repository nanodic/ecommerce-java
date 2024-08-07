package com.example.ecommercefinal.controllers;

import com.example.ecommercefinal.entities.Invoice;
import com.example.ecommercefinal.services.InvoiceServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/invoices")
@Tag(name = "InvoiceController", description = "CRUD Facturas")
public class InvoiceController {

    @Autowired
    private InvoiceServices invoiceServices;

    @PostMapping
    @Operation(summary = "Generate Invoice / Factura ", description = "Genera una nueva factura con los carritos no entregados al cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Devuelve la factura generada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",content = @Content),
    })
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
    @Operation(summary = "Find Invoice / Factura ", description = "Devuelve la ultima factura generada a un cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve la ultima factura registrada"),
            @ApiResponse(responseCode = "404", description = "No devuelve datos", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",content = @Content),
    })
    public ResponseEntity<Invoice> getLastInvoiceByClient(@PathVariable Integer cltId)
    {
        try{
            Invoice invoice = invoiceServices.getLastInvoiceByClient(cltId);
            return ResponseEntity.ok(invoice);
        }
        catch (RuntimeException e)
        {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
