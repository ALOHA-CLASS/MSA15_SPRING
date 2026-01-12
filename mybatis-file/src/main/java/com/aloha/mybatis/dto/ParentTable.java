package com.aloha.mybatis.dto;

public enum ParentTable {
  BOARD("board");
  // TODO
  // USER("user"), ...

  private final String tableName;

  ParentTable(String tableName) {
    this.tableName = tableName;
  }

  public String value() {
      return tableName;
  }
}
