package com.aloha.spring_di2.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.aloha.spring_di2.dto.Comment;

@Repository   // DAO 역할로 빈 등록
public class CommentDAO {

  public List<Comment> list() {
    List<Comment> comments = new ArrayList<>();
    Comment comment1 
      = new Comment(1L, UUID.randomUUID().toString(),
                      "작성자", "댓글내용", new Date(), new Date());
    Comment comment2
      = new Comment(2L, UUID.randomUUID().toString(),
                      "작성자", "댓글내용", new Date(), new Date());
    comments.add(comment1);
    comments.add(comment2);
    return comments;
  }
  
}
