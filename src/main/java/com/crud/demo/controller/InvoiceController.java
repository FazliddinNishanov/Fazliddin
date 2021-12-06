package com.crud.demo.controller;

import com.crud.demo.service.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvoiceController {

    private final InvoiceService service;

    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    @GetMapping("/expired_invoices")
    public ResponseEntity<?> getAllExpiredInvoices() {
        return ResponseEntity.ok(service.getAllExpiredInvoices());
    }

    @GetMapping("/wrong_date_invoices")
    public ResponseEntity<?> getAllBeforeOrder(){
        return ResponseEntity.ok(service.getAllBefore());
    }

    @GetMapping("/orders_without_details")
    public ResponseEntity<?> getAllBeforeS(){
        return ResponseEntity.ok(service.getAllBeforeSept());
    }

    @GetMapping("/customers_without_orders")
    public ResponseEntity<?> getAllBeforeC(){
        return ResponseEntity.ok(service.getAllBeforeCertain());
    }

    @GetMapping("/customers_last_orders")
    public ResponseEntity<?> getAllBeforeL(){
        return ResponseEntity.ok(service.getAllBeforeLast());
    }

    @GetMapping("/overpaid_invoices")
    public ResponseEntity<?> getAllRepeated(){
        return ResponseEntity.ok(service.getAllBeforeRepeated());
    }

    @GetMapping("/high_demand_products")
    public ResponseEntity<?> getAllAQ(){
        return ResponseEntity.ok(service.getAllAfterQuantity());
    }

    @GetMapping("/number_of_products_in_year")
    public ResponseEntity<?> getAllCS(){
        return ResponseEntity.ok(service.getAllAfterCS());
    }

    @GetMapping("/orders_without_invoices")
    public ResponseEntity<?> get10(){
        return ResponseEntity.ok(service.getAll10());
    }

}
