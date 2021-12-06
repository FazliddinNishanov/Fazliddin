package com.crud.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Setter
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 10)
    private String name;

    @ManyToOne
    private Category category;

    @Column(length = 20)
    private String description;

    @Column(precision = 6, scale = 2)
    private BigDecimal price;

    @Column(length = 1024)
    private String photo;

}
