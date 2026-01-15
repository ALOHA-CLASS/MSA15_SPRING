package com.aloha.product.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.aloha.product.dto.Product;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
  
}
