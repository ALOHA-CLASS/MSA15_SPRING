package com.aloha.product_rest.service;

import org.springframework.stereotype.Service;

import com.aloha.product_rest.dto.Product;
import com.aloha.product_rest.mapper.ProductMapper;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product, ProductMapper> implements ProductService {

  public ProductServiceImpl(ProductMapper mapper) {
    super(mapper);
  }
  
}
