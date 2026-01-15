package com.aloha.product_rest.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.aloha.product_rest.dto.Product;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
  
}
