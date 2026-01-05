package com.aloha.spring_di2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aloha.spring_di2.service.PostService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class HomeController {

  @Autowired
  // @Qualifier("boardServiceImpl")   // 빈 이름을 지정하여 주입할 빈을 명시
  // @Qualifier("commentServiceImpl")    // 빈 이름을 지정하여 주입할 빈을 명시
  // @Qualifier("B")  // BoardServiceImpl 빈 주입
  // @Qualifier("C")  // CommentServiceImpl 빈 주입
  private PostService postService;

  @RequestMapping(value = "/", method=RequestMethod.GET)
  public String home() {
    postService.list();
    return "index";
  }
  
  
}
