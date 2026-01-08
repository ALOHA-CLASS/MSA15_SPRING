package com.aloha.spring_mvc.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aloha.spring_mvc.dto.Board;
import com.aloha.spring_mvc.dto.Person;
import com.aloha.spring_mvc.dto.PersonDTO;
import com.aloha.spring_mvc.dto.User;
import com.aloha.spring_mvc.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
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

  // ============ ⚡ 요청 처리 ===========
  @ResponseBody
  @GetMapping("/header")
  public String header(@RequestHeader("Accept") String accept
                      ,@RequestHeader("User-Agent") String userAgent
                      ,HttpServletRequest request
  ) {
    // @RequestHeader 를 통한 헤더 정보 가져오기
    log.info("[GET] - /request/header");
    log.info("@RequestHeader 를 통한 헤더 정보 가져오기");
    log.info("Accept - {}", accept);
    log.info("User-Agent - {}", userAgent);
    
    // request 객체로부터 헤더 가져오기
    String requestAccept = request.getHeader("Accept");
    String requestUserAgent = request.getHeader("User-Agent");
    log.info("request 객체로부터 헤더 가져오기");
    log.info("Accept - {}", requestAccept);
    log.info("User-Agent - {}", requestUserAgent);
    return "SUCCESS";
  }

  /**
	 * 요청 본문 가져오기
	 * @param board
	 * @return
	 * * @RequestBody
	 *   : HTTP 요청 메시지의 본문(body) 내용을 객체로 변환하는 어노테이션
	 *     주로, 클라이언트에서 json 형식으로 보낸 데이터를 객체로 변환하기 위해 사용한다.
	 *     * 생략가능 (주로 생략하고 쓴다.)
	 *     
	 *   415 에러 - 지원되지 않는 미디어 타입
	 *   (Unsupported Media Type)
	 *   : 클라이언트가 보낸 컨텐츠 타입의 요청을 서버가 처리할 수 없을 때 발생하는 에러
	 *   [클라이언트] ( application/x-www-form-urlencoded )
	 *       ↓
	 *   [ 서  버 ]  ( application/json )
	 *   * @RequestBody 를 쓰면, 본문의 컨텐츠 타입을 application/json 을 기본으로 지정
	 *   
	 *   * 비동기 또는 thunder client 로 테스트 가능
	 *   Content-Type : application/json
	 *   body {  "title" : "제목",  "writer" : "작성자",  "content" : "내용" }
	 */
	@ResponseBody
	// @RequestMapping(value = "/body", method = RequestMethod.POST)
  @PostMapping("/body")
	public String requestBody(@RequestBody Board board) {
		log.info("[POST] - /request/body");
		log.info(board.toString());
		
		return "SUCCESS";
	}
  

  /**
   * ✅ 체크박스 데이터 가져오기
   * * 체크박스 다중 데이터는 배열로 전달 받을 수 있다.
   * * 같은 이름의 요청파라미터(name)들은 배열 또는 리스트로 전달 받을 수 있다.
   * @param hobbies
   * @return
   */
  @ResponseBody
  @PostMapping("/check")
  public String requestCheck(@RequestParam("hobby") String[] hobbies) {
    log.info("[POST] - /request/check");
    for (String hobby : hobbies) {
      log.info("hobby : {}", hobby);
    }
    return "SUCCESS";
  }

  @ResponseBody
  @PostMapping("/check/person")
  public String requestCheck(Person person) {
    log.info("[POST] - /request/check/person");
    List<String> hobbies = person.getHobby();
    for (String hobby : hobbies) {
      log.info("hobby : {}", hobby);
    }
    log.info("[person]");
    log.info(person.toString());
    return "SUCCESS";
  }

  @ResponseBody
  @PostMapping("/check/personDTO")
  public String requestCheckPersonDTO(PersonDTO personDTO) {
    log.info("[POST] - /request/check/personDTO");

    log.info("::::: personDTO :::::");
    log.info(personDTO.toString());

    List<String> hobbies = personDTO.getPerson().getHobby();
    for (String hobby : hobbies) {
      log.info("hobby : {}", hobby);
    }
    return "SUCCESS";
  }
  

  /**
   * Map 컬렉션으로 여러 요청 파라미터 가져오기
   * 요청 경로 : /request/map?name=김조은&age=20
   * @param map
   * @return
   */
  @ResponseBody
  @GetMapping("/map")
  public String requestMap(@RequestParam Map<String, String> map) {
    String name = map.get("name");
    String age = map.get("age");
    log.info("name : {}", name);
    log.info("age : {}", age);
    return "SUCCESS";
  }
  
  @Value("${upload.path}")
  private String uploadPath;

  /**
   * 파일 업로드
   * @return
   * @throws IOException 
   */
  @ResponseBody
  @PostMapping("/file")
  public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
    log.info("/request/file");
    log.info("uploadPath : {}", uploadPath);

    if( file == null ) return "FAIL";

    log.info("원본파일명 : {}", file.getOriginalFilename());
    log.info("용량(Byte) : {}", file.getSize());
    log.info("컨텐츠타입 {}", file.getContentType());

    // 파일 데이터
    byte[] fileData = file.getBytes();

    // 파일 업로드
    String fileName = file.getOriginalFilename();
    File uploadFile = new File(uploadPath, fileName);
    FileCopyUtils.copy(fileData, uploadFile);   // 파일복사(업로드)⭐
    // FileCopyUtils.copy(파일데이터, 파일객체);
    // : 내부적으로는 InputStream, OutputStream 을 이용하여 입력받은 파일을 출력한다.

    return "SUCCESS - 업로드 경로 : " + uploadPath;
  }


  /**
   * 다중 파일 업로드
   * @return
   * @throws IOException 
   */
  @ResponseBody
  @PostMapping("/file/multi")
  public String fileUpload(@RequestParam("file") MultipartFile[] fileList) throws IOException {
    log.info("/request/file/multi");
    log.info("uploadPath : {}", uploadPath);

    if( fileList == null ) return "FAIL";

    if( fileList.length > 0 ) {
      for (MultipartFile file : fileList) {
        log.info("원본파일명 : {}", file.getOriginalFilename());
        log.info("용량(Byte) : {}", file.getSize());
        log.info("컨텐츠타입 {}", file.getContentType());
    
        // 파일 데이터
        byte[] fileData = file.getBytes();
    
        // 파일 업로드
        String fileName = file.getOriginalFilename();
        File uploadFile = new File(uploadPath, fileName);
        FileCopyUtils.copy(fileData, uploadFile);   // 파일복사(업로드)⭐
        // FileCopyUtils.copy(파일데이터, 파일객체);
        // : 내부적으로는 InputStream, OutputStream 을 이용하여 입력받은 파일을 출력한다.
      }
    }
    return "SUCCESS - 업로드 경로 : " + uploadPath;
  }
  

  /**
   * 데이터 + 파일 업로드
   * @return
   * @throws IOException 
   */
  @ResponseBody
  @PostMapping("/file/board")
  public String fileUpload(Board board) throws IOException {
    log.info("/request/file/board");
    log.info("uploadPath : {}", uploadPath);
    log.info("board : {}", board);

    List<MultipartFile> fileList = board.getFileList();
    if( fileList == null ) return "FAIL";

    if( !fileList.isEmpty() ) {
      for (MultipartFile file : fileList) {
        log.info("원본파일명 : {}", file.getOriginalFilename());
        log.info("용량(Byte) : {}", file.getSize());
        log.info("컨텐츠타입 {}", file.getContentType());
    
        // 파일 데이터
        byte[] fileData = file.getBytes();
    
        // 파일 업로드
        String fileName = file.getOriginalFilename();
        File uploadFile = new File(uploadPath, fileName);
        FileCopyUtils.copy(fileData, uploadFile);   // 파일복사(업로드)⭐
        // FileCopyUtils.copy(파일데이터, 파일객체);
        // : 내부적으로는 InputStream, OutputStream 을 이용하여 입력받은 파일을 출력한다.
      }
    }
    return "SUCCESS - 업로드 경로 : " + uploadPath;
  }

  // ajax 비동기 파일 업로드 화면
  @GetMapping("/ajax")
  public String ajx() {
      return "request/ajax";
  }

  /**
   * 데이터 + 파일 업로드 (ajax)
   * @return
   * @throws IOException 
   */
  @ResponseBody
  @PostMapping("/file/ajax")
  public String fileUploadAjax(Board board) throws IOException {
    log.info("/request/file/ajax");
    log.info("uploadPath : {}", uploadPath);
    log.info("board : {}", board);

    List<MultipartFile> fileList = board.getFileList();
    if( fileList == null ) return "FAIL";

    if( !fileList.isEmpty() ) {
      for (MultipartFile file : fileList) {
        log.info("원본파일명 : {}", file.getOriginalFilename());
        log.info("용량(Byte) : {}", file.getSize());
        log.info("컨텐츠타입 {}", file.getContentType());
    
        // 파일 데이터
        byte[] fileData = file.getBytes();
    
        // 파일 업로드
        String fileName = file.getOriginalFilename();
        File uploadFile = new File(uploadPath, fileName);
        FileCopyUtils.copy(fileData, uploadFile);   // 파일복사(업로드)⭐
        // FileCopyUtils.copy(파일데이터, 파일객체);
        // : 내부적으로는 InputStream, OutputStream 을 이용하여 입력받은 파일을 출력한다.
      }
    }
    return "SUCCESS - 업로드 경로 : " + uploadPath;
  }

  @Autowired 
  private UserService userService;

  /**
   * 날짜(date) 형식 요청 파라미터 바인딩하기
   * @return
   */
  @ResponseBody
  @PostMapping("/user")
  // 1️⃣
  // public String requestUser(@RequestParam("birth") String birth) {
  // 2️⃣
  // public String requestUser(
  //   @RequestParam("birth") 
  //   // @DateTimeFormat(pattern = "yyyy-MM-dd") Date birth
  //   LocalDate birth   // 자동으로 변환 및 바인딩 됨
  // ) {
  // 3️⃣
  public String requestUser(User user) {
    log.info("[POST] - /request/user");
    // log.info("birth : {}", birth);  // 2026-01-08
    log.info("user : {}", user);
    
    boolean result = userService.signup(user);
    log.info("result : {}", result);
    return "SUCCESS";
  }
  

}
