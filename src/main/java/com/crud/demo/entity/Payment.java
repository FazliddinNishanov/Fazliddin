package com.crud.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Timestamp time;

    @Column(precision = 8, scale = 2, nullable = false)
    private BigDecimal amount;

    @ManyToOne
    private Invoice inv;

}
