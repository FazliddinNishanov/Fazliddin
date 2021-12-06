package com.crud.demo.repository;

import com.crud.demo.entity.Invoice;
import com.crud.demo.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
        Optional<Payment> findByInv(Invoice invoice);
}
