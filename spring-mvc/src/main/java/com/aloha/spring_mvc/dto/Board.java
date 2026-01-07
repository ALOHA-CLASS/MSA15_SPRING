package com.aloha.spring_mvc.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Board {
  private Long no;
  private String title;
  private String writer;
  private String content;
  private Date createdAt;
  private Date updatedAt;

  // 파일 데이터
  // private MultipartFile[] fileList;
  private List<MultipartFile> fileList;

  public Board(Long no, String title, String writer, String content) {
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.content = content;
	}
}
