package com.aloha.mybatis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aloha.mybatis.dto.Files;
import com.aloha.mybatis.service.FileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileController {

  private final FileService fileService;

  /**
   * 파일 뷰어(썸네일)
   * @param id
   * @return
   * @throws Exception
   */
  @GetMapping("/{id}")
  public ResponseEntity<Resource> viewFile(@PathVariable("id") String id) throws Exception {

    Files file = fileService.selectById(id);

    if( file == null ) {
      return ResponseEntity.notFound().build();
    }

    FileSystemResource resource = new FileSystemResource(file.getPath());

    if (!resource.exists()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok()
          .header(HttpHeaders.CONTENT_DISPOSITION,
                  "inline; filename=\"" + file.getName() + "\"")
          .contentType(MediaType.parseMediaType(file.getContentType()))
          .body(resource);
  }

  /**
   * 파일 삭제
   * @param id
   * @return
   * @throws Exception
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Map<?, ?>> deleteFile(@PathVariable("id") String id) throws Exception {
    boolean result = fileService.deleteById(id);
    if (!result) {
      return ResponseEntity.notFound().build();
    }
    Map<String, Object> response = new HashMap<>();
    response.put("success", true);
    return ResponseEntity.ok(response);
  }


  @PutMapping("/sort")
  public ResponseEntity<Map<?, ?>> fileSort(@RequestBody List<Map<String, Object>> stack) throws Exception {
    log.info("stack {}", stack);
    boolean result = fileService.updateSortOrder(stack);
    log.info("result {}", result);
    if( !result ) {
      throw new Exception("파일 순서 변경에 실패했습니다.");
    }
    Map<String, Object> response = new HashMap<>();
    response.put("success", true);
    return ResponseEntity.ok(response);
  }

}
