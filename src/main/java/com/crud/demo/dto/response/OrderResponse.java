package com.crud.demo.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponse {
    String status;
    Integer invoiceNumber;
}
