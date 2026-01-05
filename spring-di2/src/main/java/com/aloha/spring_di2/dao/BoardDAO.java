package com.aloha.spring_di2.dao;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.aloha.spring_di2.dto.Board;

@Repository   // DAO 역할로 빈 등록
public class BoardDAO {
  
  public List<Board> list() {
    List<Board> list = List.of(
      new Board(1L, UUID.randomUUID().toString(), "제목", "내용", "작성자", new Date(), new Date()),
      new Board(2L, UUID.randomUUID().toString(), "제목", "내용", "작성자", new Date(), new Date()),
      new Board(3L, UUID.randomUUID().toString(), "제목", "내용", "작성자", new Date(), new Date())
    );
    return list;
  }

}
