package com.crud.demo.controller;

import com.crud.demo.entity.Product;
import com.crud.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProductController {

    public final ProductService productService;

    @GetMapping("/product/list")
    public ResponseEntity<?> getAllProduct(){
       List<Product> productList =  productService.takeAllProducts();
       return ResponseEntity.ok(productList);
    }

    @GetMapping("product/details")
    public ResponseEntity<?> getOnePr(@RequestParam(name = "product_id") Integer id){
        Optional<Product> product = productService.getOneProduct(id);
        return ResponseEntity.ok(product);
    }

}
