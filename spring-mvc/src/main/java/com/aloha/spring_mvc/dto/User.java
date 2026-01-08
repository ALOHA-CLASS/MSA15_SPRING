package com.aloha.spring_mvc.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class User {
  private Long no;
  private String id;
  private String username;
  private String password;
  private String name;

  // @DateTimeFormat 붙여야 변환됨
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date birth;
  
  // 자동으로 변환됨
  // private LocalDate birth;
  
}
