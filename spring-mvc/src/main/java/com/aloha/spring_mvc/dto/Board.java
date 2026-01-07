package com.aloha.spring_mvc.dto;

import java.util.Date;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Board {
  private final Long no;
  private final String title;
  private final String writer;
  private final String content;
  private Date createdAt;
  private Date updatedAt;

  // public Board(Long no, String title, String writer, String content) {
	// 	this.no = no;
	// 	this.title = title;
	// 	this.writer = writer;
	// 	this.content = content;
	// }
}
