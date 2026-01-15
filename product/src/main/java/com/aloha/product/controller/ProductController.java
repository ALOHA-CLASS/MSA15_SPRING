package com.aloha.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aloha.product.dto.Pagination;
import com.aloha.product.dto.Product;
import com.aloha.product.service.ProductService;
import com.github.pagehelper.PageInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;
  
  @GetMapping()
  public ResponseEntity<?> getAll(Pagination pagination) {
    try {
      PageInfo<Product> pageInfo = productService.page(pagination);
      return new ResponseEntity<>(pageInfo, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<?> getOne(@PathVariable("id") String id) {
    try {
      Product product = productService.selectById(id);
      return new ResponseEntity<>(product, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @PostMapping()
  public ResponseEntity<?> create(@RequestBody Product product) {
    try {
      boolean result = productService.insert(product);
      if( !result ) {
        return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
      }
      return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>("EXCEPTION", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @PutMapping()
  public ResponseEntity<?> update(@RequestBody Product product) {
    try {
      boolean result = productService.update(product);
      if( !result ) {
        return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
      }
      return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>("EXCEPTION", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @DeleteMapping("/{id}") 
  public ResponseEntity<?> destroy(@PathVariable("id") String id) {
    try {
      boolean result = productService.deleteById(id);
      if( !result ) {
        return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
      }
      return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>("EXCEPTION", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
}
