package com.crud.demo.dto.response;

import com.crud.demo.entity.Payment;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponse {
    String paymentStatus;
    Payment paymentDetails;
}
