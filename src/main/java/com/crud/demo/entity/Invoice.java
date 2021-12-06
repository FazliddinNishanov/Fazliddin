package com.crud.demo.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Setter
@Getter
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Order ord;

    @Column(precision = 8, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private Timestamp issued;

    @Column(nullable = false)
    private Timestamp due;
}
