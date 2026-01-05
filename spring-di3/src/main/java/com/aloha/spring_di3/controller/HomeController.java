package com.aloha.spring_di3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aloha.spring_di3.dto.Product;
import com.aloha.spring_di3.dto.Users;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class HomeController {

  // @Autowired private Users user;
  // @Autowired private Product product;

  // 생성자 주입(권장⭐)
  private final Users user;
  private final Product product;

  // @Autowired (Spring 4.3 이후부터는 단일 생성자에 한해 @Autowired 생략 가능)
  public HomeController(Users user, Product product) {
    this.user = user;
    this.product = product;
  }

  @RequestMapping(value = "/", method=RequestMethod.GET)
  public String home(Model model) {
    log.info("user : {}", user);
    log.info("product : {}", product);

    // 뷰에 전달할 데이터를 모델에 추가한다.
    model.addAttribute("user", user);
    model.addAttribute("product", product);
    return "index"; // index.html 뷰를 지정한다.
  }
  
  
}
