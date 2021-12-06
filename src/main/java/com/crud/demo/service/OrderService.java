package com.crud.demo.service;

import com.crud.demo.dto.request.MakeOrderRequest;
import com.crud.demo.dto.response.OrderResponse;
import com.crud.demo.entity.*;
import com.crud.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    public final OrderRepository orderRepository;
    public final CustomerRepository customerRepository;
    public final ProductsRepository productsRepository;
    public final DetailRepository detailRepository;
    private final InvoiceRepository invoiceRepository;

    public Optional<Object> getAllBefore(Integer id) {
        return Optional.ofNullable(orderRepository.findByPrNameOrderAll(id));
    }

    @Transactional(rollbackFor = Exception.class)
    public OrderResponse makeOrder(MakeOrderRequest makeOrderRequest) {
        var orderResponseBuilder = OrderResponse.builder();
        Optional<Customer> optionalCustomer = customerRepository.findById(makeOrderRequest.getCustomerId());
        if (optionalCustomer.isEmpty()) {
            return orderResponseBuilder.status("FAILED").build();
        }

        Order newOrder = new Order();
        newOrder.setCust(optionalCustomer.get());
        newOrder.setDate(Timestamp.valueOf(LocalDateTime.now()));

        Optional<Product> optionalProduct = productsRepository.findById(makeOrderRequest.getProductId());
        if (optionalProduct.isEmpty()) {
            return orderResponseBuilder.status("FAILED").build();
        }

        Detail newDetail = Detail.builder()
                .pr(optionalProduct.get())
                .quantity(makeOrderRequest.getQuantity())
                .build();

        newOrder = orderRepository.save(newOrder);

        newDetail.setOrd(newOrder);

        detailRepository.save(newDetail);

        LocalDateTime now = LocalDateTime.now();

        Invoice newInvoice = Invoice.builder()
                .ord(newOrder)
                .amount(optionalProduct.get().getPrice().multiply(BigDecimal.valueOf(makeOrderRequest.getQuantity())))
                .issued(Timestamp.valueOf(LocalDateTime.now()))
                .due(Timestamp.valueOf(LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth()+7, now.getHour(), now.getMinute())))
                .build();
        newInvoice = invoiceRepository.save(newInvoice);

        return OrderResponse.builder()
                .status("SUCCESS")
                .invoiceNumber(newInvoice.getId())
                .build();
    }
}
