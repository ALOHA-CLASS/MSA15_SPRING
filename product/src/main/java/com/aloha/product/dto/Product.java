package com.aloha.product.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Alias("Product")
public class Product extends Base {
  
  private String name;
  private Integer price;
  private Integer stock;

}
