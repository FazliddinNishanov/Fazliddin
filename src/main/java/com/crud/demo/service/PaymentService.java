package com.crud.demo.service;

import com.crud.demo.dto.request.MakePaymentRequest;
import com.crud.demo.dto.response.PaymentResponse;
import com.crud.demo.entity.Invoice;
import com.crud.demo.entity.Payment;
import com.crud.demo.repository.InvoiceRepository;
import com.crud.demo.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    public final PaymentRepository paymentRepository;
    public final InvoiceRepository invoiceRepository;

    public Optional<Payment> getOnePaymentDetail(Integer id) {
        return paymentRepository.findById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public PaymentResponse makePayment(MakePaymentRequest makePaymentRequest) {
        var paymentResponseBuilder = PaymentResponse.builder();
        Optional<Invoice> invoice = invoiceRepository.findById(makePaymentRequest.getInvoiceId());
        if (invoice.isEmpty()) {
            return paymentResponseBuilder.paymentStatus("FAILED FIRST").build();
        }

        Payment newPayment = new Payment();
        newPayment.setInv(invoice.get());
        newPayment.setAmount(invoice.get().getAmount());
        newPayment.setTime(Timestamp.valueOf(LocalDateTime.now()));

        newPayment = paymentRepository.save(newPayment);

        return PaymentResponse.builder()
                .paymentStatus("SUCCESS")
                .paymentDetails(newPayment)
                .build();
    }

}
