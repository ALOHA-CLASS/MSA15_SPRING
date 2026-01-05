package com.aloha.spring_di2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aloha.spring_di2.dao.CommentDAO;
import com.aloha.spring_di2.dto.Comment;
import com.aloha.spring_di2.dto.Post;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("C")   // 빈이름 : C
public class CommentServiceImpl implements PostService {

  @Autowired
  private CommentDAO commentDAO;

  @Override
  public List<Post> list() {
    List<Comment> commentList = commentDAO.list();
    List<Post> postList = new ArrayList<>(commentList);
    log.info("commentList : {}",commentList);
    return postList;
  }
  
}
