package com.crud.demo.controller;

import com.crud.demo.dto.request.MakePaymentRequest;
import com.crud.demo.dto.response.PaymentResponse;
import com.crud.demo.entity.Payment;
import com.crud.demo.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    public final PaymentService paymentService;

    @GetMapping("/payment/details")
    public ResponseEntity getOnePayment(@RequestParam(name = "id") Integer id){
        Optional<Payment> payment = paymentService.getOnePaymentDetail(id);
        return ResponseEntity.ok(payment);
    }

    @PostMapping("/payment")
    public ResponseEntity<PaymentResponse> makePay(@RequestBody MakePaymentRequest makePaymentRequest){
       return ResponseEntity.ok(paymentService.makePayment(makePaymentRequest));
    }

}
