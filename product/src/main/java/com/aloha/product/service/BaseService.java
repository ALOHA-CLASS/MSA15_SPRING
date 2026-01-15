package com.aloha.product.service;

import java.util.List;

import com.aloha.product.dto.Pagination;
import com.github.pagehelper.PageInfo;

public interface BaseService<E> {

  List<E> list();                                               // 전체 리스트
  PageInfo<E> page(Pagination pagination);                      // 페이징 리스트
  E select(Long no);                                            // no(PK) 조회
  E selectById(String id);                                      // id(PK) 조회
  boolean insert(E entity);                                     // 생성
  boolean update(E entity);                                     // 수정
  boolean updateById(E entity);                                 // id(PK) 수정
  boolean delete(Long no);                                      // no(PK) 삭제
  boolean deleteById(String id);                                // id(PK) 삭제
  
}
