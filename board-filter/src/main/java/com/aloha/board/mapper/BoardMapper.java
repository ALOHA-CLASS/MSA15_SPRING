package com.aloha.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.aloha.board.dto.Board;
import com.aloha.board.dto.Pagination;
import com.aloha.board.dto.Params;

@Mapper
public interface BoardMapper {
  // 게시글 목록
  List<Board> list() throws Exception;
  // 게시글 목록
  List<Board> listWithParam(Params params) throws Exception;
  // 페이징 목록 ⚡
  List<Board> page(Pagination pagination) throws Exception;
  // 페이징 목록 ⚡
  List<Board> pageWithParam(Params params) throws Exception;
  // 페이징 목록 ⚡
  List<Board> pageWithPaginationAndParam(
    @Param("pagination") Pagination pagination, @Param("params") Params params) throws Exception;
  // 게시글 조회
  Board select(Integer no) throws Exception;
  // 게시글 등록
  int insert(Board board) throws Exception;
  // 게시글 수정
  int update(Board board) throws Exception;
  // 게시글 삭제
  int delete(Integer no) throws Exception;
  // 데이터 수
  long count() throws Exception;
}