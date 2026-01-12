package com.aloha.mybatis.dto;

import java.util.Date;
import java.util.UUID;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("Files")
public class Files {
  private Integer no;
  private String id;
  private String parentTable;
  private Integer parentNo;
  private String name;
  private String path;
  private Long size;
  private String contentType;
  private Integer sortOrder;
  private Boolean isMain;
  private Date createdAt;
  private Date updatedAt;

  public Files() {
    this.id = UUID.randomUUID().toString();
  }

  
}
