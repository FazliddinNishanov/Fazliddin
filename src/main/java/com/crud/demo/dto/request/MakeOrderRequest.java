package com.crud.demo.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class MakeOrderRequest {
    private Integer customerId;
    private Integer productId;

    @Min(value = 1)
    private int quantity;
}
