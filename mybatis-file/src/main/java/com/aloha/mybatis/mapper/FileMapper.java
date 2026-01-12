package com.aloha.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.aloha.mybatis.dto.Files;

@Mapper
public interface FileMapper {
  // 파일 목록
  List<Files> list() throws Exception;
  // 파일 조회
  Files select(Integer no) throws Exception;
  Files selectById(String id) throws Exception;
  // 파일 등록
  int insert(Files file) throws Exception;
  // 파일 수정
  int update(Files file) throws Exception;
  int updateById(Files file) throws Exception;
  // 파일 삭제
  int delete(Integer no) throws Exception;
  int deleteById(String id) throws Exception;

  // ⭐ 부모 기준 목록
  // List<Files> listByParent(@Param("parentTable") String parentTable, @Param("parentNo") Integer parentNo) throws Exception;
  List<Files> listByParent(Files files) throws Exception;
  
  // ⭐ 부모 파일 삭제
  // int deleteByParent(@Param("parentTable") String parentTable, @Param("parentNo") Integer parentNo) throws Exception;
  int deleteByParent(Files files) throws Exception;

}
