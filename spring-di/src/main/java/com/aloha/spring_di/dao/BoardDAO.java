package com.aloha.spring_di.dao;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository     // DAO 역할로 빈 등록
public class BoardDAO {
  
  public void test() {
    log.info("BoardDAO test() 실행");
  }

}
