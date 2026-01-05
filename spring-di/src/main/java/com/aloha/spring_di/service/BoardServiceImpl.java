package com.aloha.spring_di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aloha.spring_di.dao.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
  // 1️⃣
  // 기본 의존성 자동 주입
  @Autowired
  private BoardDAO boardDAO;

  // 2️⃣
  // 생성자 주입
  // private final BoardDAO boardDAO;

  // // @Autowired       (생략 가능)
  // public BoardServiceImpl(BoardDAO boardDAO) {
  //   this.boardDAO = boardDAO;
  // }

  @Override
  public void test() {
    boardDAO.test();
  }

  // 3️⃣
  // 세터 주입
  // private BoardDAO boardDAO;

  @Autowired
  @Override
  public void setDAO(BoardDAO boardDAO) {
    this.boardDAO = boardDAO;
  }


  
}
