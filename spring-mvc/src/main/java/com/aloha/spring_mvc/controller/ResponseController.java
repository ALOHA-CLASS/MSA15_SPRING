package com.aloha.spring_mvc.controller;

import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aloha.spring_mvc.dto.Board;
import com.aloha.spring_mvc.dto.Person;
import com.aloha.spring_mvc.service.BoardService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;





@Slf4j
@RequiredArgsConstructor
@Controller
public class ResponseController {

  private final BoardService boardService;

  /**
   * ⭐void 
   * : 요청 경로와 같은 이름의 뷰를 응답한다.
   * 요청 경로 : /response/index
   * 응답      : /response/index.html
   */
  @GetMapping("/response/index")
  public void response() {
    log.info("void 타입 - /response/index");
    log.info("/response/index.html 뷰를 응답");
  }

  /**
   * ⭐String
   * : 뷰이름을 반환값으로 지정하여 뷰를 응답한다.
   * 요청 경로 : /response/view
   * 응답      : /response/index.html
   */
  @GetMapping("/response/view")
  public String responseView() {
    log.info("String 타입 - /response/view");
    log.info("/response/index.html 뷰를 응답");
    return "response/index";    // 응답할 뷰 이름 지정
  }
  
  /**
   * ⭐ModelAndView
   * : model 과 view 를 동시에 처리 (데이터 등록과 화면 지정)
   * @param mv
   * @return
   */
  @GetMapping("/response/model/view")
  public ModelAndView responseModelAndView(ModelAndView mv) {
    log.info("ModelAndView 타입 - /response/model/view");
    log.info("/response/index.html 뷰를 응답");
    // 뷰 지정
    mv.setViewName("response/index");
    // 모델 등록
    Person person = new Person();
    person.setName("김조은");
    person.setAge(20);
    mv.addObject("person", person);
    mv.addObject("message", "ModelAndView 데이터...");
    return mv;
  }


  /**
   * ⭐클래스
   * - 요청 경로 : /response/data/board
   * - 응답      : board (XML/JSON)
   * @ResponseBody : 응답하는 데이터를 본문(body) 에 지정하는 어노테이션
   * * Accept 헤더가 기본적으로 html, xml 우선순위라서 XML 로 응답
   * @return
   * @throws Exception
   */
  @ResponseBody
  @GetMapping("/response/data/board")
  public Board responseBoard() throws Exception {
    Board board = boardService.select(1L);
    return board;
  }
  

  /**
   * JSON 으로 직접 데이터를 세팅하여 응답하기
   * Board ➡ JSON
   * @return
   * @throws Exception 
   */
  @ResponseBody
  @GetMapping("/response/data/board/json")
  public void responseJSONBoard(HttpServletResponse response) throws Exception {
    Board board = boardService.select(1L);
    response.setContentType("application/json");
    // 객체를 JSON문자열로 변환
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(board);
    PrintWriter out = response.getWriter();
    out.print(json);
    out.flush();
  }
  
  /**
   * ⭐컬렉션
   * @return
   * @throws Exception
   * produces = "application/json"
   * - Accept: application/xml 요청이 와도, json 으로 응답할 수 있다.
   */
  @ResponseBody
  @GetMapping(value = "/response/data/board/list", produces = "application/json")
  public List<Board> responseBoardList() throws Exception {
    List<Board> list = boardService.list();
    return list;
  }
  

  /**
   * ⭐컬렉션(Map)
   * @return
   */
  @ResponseBody
  @GetMapping(value = "/response/data/map", produces = "application/json")
  public Map<String, Board> responseMap() {
    Map<String, Board> map = new HashMap<>();
    map.put("board1", new Board(1L, "A", "B", "C"));
    map.put("board2", new Board(2L, "A", "B", "C"));
    map.put("board3", new Board(3L, "A", "B", "C"));
    return map;
  }
  
  /**
   * ⭐ResponseEntity<Void>
   * * ResponseEntity
   * : 응답 헤더,본문,상태코드 등을 지정하는 객체
   * * ResponseEntity<Void>
   * : 헤더,상태코드를 지정하여 응답 처리
   * * HttpStatus 열거타입(ENUM)
   * : 상태코드를 가지고 있는 열거타입
   * - OK                       : 200
   * - CREATED                  : 201
   * - NOT_FOUND                : 404
   * - INTERNAL_SERVER_ERROR    : 500
   * @return
   */
  @GetMapping("/response/data/entity/void")
  public ResponseEntity<Void> responseEntityVoid() {
    // return ResponseEntity.ok().build();
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/response/data/entity/string")
  public ResponseEntity<String> responseEntityString() {
    // return new ResponseEntity<>(응답본문, 상태코드);
    return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
  }

  @GetMapping("/response/data/entity/board/list")
  public ResponseEntity<List<Board>> responseEntityBoardList() throws Exception {
    List<Board> boardList = boardService.list();
    return new ResponseEntity<>(boardList, HttpStatus.OK);
  }

  @GetMapping("/response/data/entity/map")
  public ResponseEntity<Map<String, Board>> responseEntityBoardMap() throws Exception {
    List<Board> boardList = boardService.list();
    Map<String, Board> map = new HashMap<>();
    int i = 1;
    for (Board board : boardList) {
      map.put("board" + i++, board);
    }
    return new ResponseEntity<>(map, HttpStatus.OK);
  }

  /**
   * ⭐ResponseEntity<?>
   * - 타입매개변수를 ? 와일드카드로 지정하면,
   *   응답 타입을 유연하게 사용할 수 있다.
   * ⚡ResponseEntity 를 사용하면, @ResponseBody 를 생략해도 된다.
   * : 뷰 해석 단계를 건너뛰고, 응답본문에 직접 처리해준다.
   * @return
   * @throws Exception
   */
  @GetMapping("/response/data/entity/random")
  public ResponseEntity<?> responseEntityRandom() throws Exception {
    List<Board> boardList = boardService.list();
    Map<String, Board> map = new HashMap<>();
    int i = 1;
    for (Board board : boardList) {
      map.put("board" + i++, board);
    }
    return new ResponseEntity<>(map, HttpStatus.OK);
  }
  

  /**
   * 파일 다운로드
   * @param param
   * @return
   * @throws UnsupportedEncodingException 
   */
  @GetMapping("/response/data/file")
  public ResponseEntity<byte[]> responseFile(HttpServletRequest request) throws UnsupportedEncodingException {
    // 파일 입력
    String path = "upload/test.png";    // resources/upload/test.png
    String fileName = "test.png";
    
    byte[] fileData = null;
    try {
      // 파일 입력
      ClassPathResource resource = new ClassPathResource(path);
      // 파일을 바이트코드로 변환
      fileData = resource.getInputStream().readAllBytes();
    } catch (Exception e) {
      e.printStackTrace();
    }
    // 헤더 설정
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType( MediaType.IMAGE_PNG );
    fileName = new String( fileName.getBytes("UTF-8") );
    headers.add("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
    
    // return new ResponseEntity<byte[]>(응답본문, 헤더, 상태코드);
    return new ResponseEntity<byte[]>(fileData, headers, HttpStatus.OK);
  }
  
}
