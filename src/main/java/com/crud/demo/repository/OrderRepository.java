package com.crud.demo.repository;

import com.crud.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(nativeQuery = true,value = "SELECT o.*, p.name FROM orders o left join detail d on o.id = d.ord_id left join product p on d.pr_id = p.id where o.id = ?1")
    Optional<Object> findByPrNameOrderAll(Integer id);

}
