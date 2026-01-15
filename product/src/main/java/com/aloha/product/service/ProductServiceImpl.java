package com.aloha.product.service;

import org.springframework.stereotype.Service;

import com.aloha.product.dto.Product;
import com.aloha.product.mapper.ProductMapper;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product, ProductMapper> implements ProductService {

  public ProductServiceImpl(ProductMapper mapper) {
    super(mapper);
  }
  
}
