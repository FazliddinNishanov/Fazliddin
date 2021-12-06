package com.crud.demo.service;

import com.crud.demo.entity.Product;
import com.crud.demo.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    public final ProductsRepository productsRepository;

    public List<Product> takeAllProducts(){
        return productsRepository.findAll();
    }

   public Optional<Product> getOneProduct(Integer id){
        return productsRepository.findById(id);
   }
}
