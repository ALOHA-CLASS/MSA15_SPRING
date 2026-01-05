package com.aloha.spring_di3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aloha.spring_di3.dto.Product;
import com.aloha.spring_di3.dto.Users;

@Configuration      // 빈을 등록하는 스프링 설정 파일로 지정
public class Config {
  // ⭐설정 클래스로 빈을 등록하는 방법
  // 1. 객체를 반환하는 메소드 정의
  // 2. 메소드에 @Bean 애노테이션 지정

  @Bean // 빈 등록
  public Users users() {
    return new Users("user", "123456");
  }

  @Bean // 빈 등록
  public Product product() {
    return new Product("티셔츠", 20000, 50);
  }
}
