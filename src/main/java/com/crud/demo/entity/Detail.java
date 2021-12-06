package com.crud.demo.entity;

import lombok.*;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Setter
@Getter
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Order ord;

    @ManyToOne
    private Product pr;

    @Column(nullable = false, columnDefinition = "smallint")
    private int quantity;
}
