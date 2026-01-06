package com.aloha.spring_mvc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller                   // Controller 로 지정하고 빈 등록
@RequestMapping("/request")   // [클래스 레벨 요청경로 매핑] : /request/~
public class RequestController {

  // ⭐ 컨트롤러 메소드

  // ⚡ 요청 경로 매핑
  /**
   * @RequestMapping : 요청 경로 매핑
   * - 요청 : /request/board
   * - 응답 : /request/board.html
   * @return
   */
  // @RequestMapping(value = "/request/board", method=RequestMethod.GET)
  // @RequestMapping(value = "/board", method=RequestMethod.GET)
  // @RequestMapping("/board")
  @GetMapping("/board")
  public String request() {
    log.info("[GET] - /request/board");
    return "request/board";
  }
  

  /**
   * 경로 패턴 매핑
   * @param no
   * @return
   */
  // @RequestMapping(value = "/board/{no}", method=RequestMethod.GET)
  @GetMapping("/board/{no}")
  public String requestPath(@PathVariable("no") Long no) {
    log.info("[GET] - /request/board/{no}");
    log.info("no : {}", no);
    return "request/board";
  }

  /**
   * 요청 메소드 매핑
   * @return
   */
  // @RequestMapping(value = "/board", method=RequestMethod.POST)
  @ResponseBody
  @PostMapping("/board")
  public String requestPost(@RequestParam(name = "no", required = false) Long no) {
    log.info("[POST] - /request/board");
    log.info("no : {}", no);
    // return "redirect:/request/board/list";
    return "SUCCESS";
  }

  @GetMapping("/board/list")
  public String requestList() {
    return "request/board/list";
  }
  

  /**
   * 파라미터 매핑
   * @param param
   * @return
   * * params 속성으로 요청 파라미터가 id 인 경우 매핑한다.
   * * /request/board?id=aloha
   * * /request/board?id=aloha&age=20
   */
  // @RequestMapping(value = "/board", method=RequestMethod.GET
  //               , params = {"id", "age"}
  // )
  @GetMapping(value = "/board", params = {"id", "age"})
  public String requestPrams(
    @RequestParam("id") String id,
    @RequestParam("age") Long age
  ) {
    log.info("[GET] - /request/board?id=" + id + "&age=" + age);
    log.info("id : {}", id);
    log.info("age : {}", age);
    return "request/board";
  }

  /**
   * 헤더 매핑
   * @param param
   * @return
   * * headers = "헤더명=값" 으로 지정하여 헤더를 매핑 조건으로 사용한다.
   */
  @ResponseBody         // 반환 값을, 응답 메시지 본문(body)에 직접 지정
  @RequestMapping(value = {"/board", "/board2"}, method=RequestMethod.POST
                  ,headers = "Content-Type=application/json"
                  // ,headers = {"헤더1", "헤더2"}
  )
  public String requestHeader() {
    log.info("[POST] - /request/board");
    log.info("헤더 매핑...");
    return "SUCCESS";
  }
  
  
  /**
   * PUT 매핑
   * @param param
   * @return
   */
  @ResponseBody
  // @RequestMapping(value = "/board", method=RequestMethod.PUT)
  @PutMapping("/board")
  public String requestPut() {
    log.info("[PUT] - /request/board");
    return "SUCCESS";
  }

  /**
   * 컨텐츠 타입 매핑
   * @return
   * - Content-Type 헤더의 값으로 매핑
   * - consumes = "컨텐츠타입값"
   */
  // @RequestMapping(value = "/board", method=RequestMethod.POST
  //                 ,consumes = "application/xml"
  // )
  @ResponseBody
  @PostMapping(value = "/board", consumes = "application/xml")
  public String requestContentType() {
    log.info("[POST] - /request/board");
    log.info("컨텐츠 타입 매핑...");
    return "SUCCESS - xml";
  }

  /**
   * Accept 매핑
   * @param param
   * @return
   * - Accept 헤더의 값으로 매핑
   * - Accept 헤더 ?
   *  : 응답 받을 컨텐츠 타입을 서버에게 알려주는 헤더
   * - produces = "컨텐츠 타입"
   */
  @ResponseBody
  // @RequestMapping(value = "/board", method=RequestMethod.POST
  //                 ,produces = "application/json"
  // )
  @PostMapping(value = "/board", produces = "application/json")
  public Map<?, ?> requestAccept() {
    log.info("[POST] - /request/board");
    log.info("Accept 매핑...");
    Map<String, String> map = new HashMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    return map;
  }
}
