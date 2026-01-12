package com.aloha.mybatis.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.aloha.mybatis.dto.Files;
import com.aloha.mybatis.dto.ParentTable;

public interface FileService {
  
  // 파일 목록
  List<Files> list() throws Exception;
  // 파일 조회
  Files select(Integer no) throws Exception;
  Files selectById(String id) throws Exception;
  // 파일 등록
  boolean insert(Files file) throws Exception;
  // 파일 수정
  boolean update(Files file) throws Exception;
  boolean updateById(Files file) throws Exception;
  // 파일 삭제
  boolean delete(Integer no) throws Exception;
  boolean deleteById(String id) throws Exception;
  // 파일 업로드
  int upload(List<MultipartFile> files, ParentTable parentTable, Integer parentNo) throws Exception;

  // ⭐ 부모 기준 목록
  List<Files> listByParent(ParentTable parentTable, Integer parentNo) throws Exception;
  List<Files> listByParent(Files files) throws Exception;
  
  // ⭐ 부모 파일 삭제
  int deleteByParent(ParentTable parentTable, Integer parentNo) throws Exception;
  int deleteByParent(Files files) throws Exception;

  // ⚡ 파일 순서 변경, 
  boolean updateSortOrder(List<Map<String, Object>> sortOrderList) throws Exception;

}
