package com.aloha.spring_di.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// 빈등록
@Component      // 클래스를 빈으로 등록하는 어노테이션
public class Board {

  private String title;
  private String writer;
  private String content;


}
