package com.aloha.spring_di.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aloha.spring_di.service.BoardService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class HomeController {

  @Autowired
  private BoardService boardService;
  
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String home() {
      boardService.test();
      return "index";
  }
  

}
