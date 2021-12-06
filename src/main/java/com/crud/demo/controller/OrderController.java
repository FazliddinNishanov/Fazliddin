package com.crud.demo.controller;

import com.crud.demo.dto.request.MakeOrderRequest;
import com.crud.demo.dto.response.OrderResponse;
import com.crud.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class OrderController {

    public final OrderService orderService;

    @GetMapping("/order/details")
    public ResponseEntity<?> getOrderByIdAndProductName(@RequestParam(name = "order_id") Integer id){
        Optional<Object> order = orderService.getAllBefore(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/order")
    public ResponseEntity<OrderResponse> makeOrder(@Valid @RequestBody MakeOrderRequest makeOrderRequest) {
        return ResponseEntity.ok(orderService.makeOrder(makeOrderRequest));
    }

}
