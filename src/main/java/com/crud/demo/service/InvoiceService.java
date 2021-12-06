package com.crud.demo.service;

import com.crud.demo.dto.InvoiceDto;
import com.crud.demo.entity.Category;
import com.crud.demo.entity.Invoice;
import com.crud.demo.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceRepository repository;


    public InvoiceService(InvoiceRepository repository) {
        this.repository = repository;
    }

    public List<Invoice> getAllExpiredInvoices() {
        return repository.findAllByIssuedAndDue();
    }

    public List<Object> getAllBefore() {
        return repository.findAllByBeforeOrder();
    }

    public List<Object> getAllBeforeSept() {
        return repository.findAllByBeforeSeptember();
    }

    public List<Object> getAllBeforeCertain() {
        return repository.findAllByNotGetCertainYear();
    }

    public List<Object> getAllBeforeLast() {
        return repository.findAllByCustomerLastOrder();
    }

    public List<Object> getAllBeforeRepeated() { return repository.findAllByInvoicesOverpaid(); }

    public List<Object> getAllAfterQuantity() { return repository.findAllByQuantity(); }

    public List<Object> getAllAfterCS() { return repository.findAllByCountrySelected(); }

    public List<Object> getAll10() { return repository.findAllBy10(); }



}

