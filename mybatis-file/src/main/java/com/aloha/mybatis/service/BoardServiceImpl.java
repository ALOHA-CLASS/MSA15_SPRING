package com.aloha.mybatis.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aloha.mybatis.dto.Board;
import com.aloha.mybatis.dto.ParentTable;
import com.aloha.mybatis.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

  private final BoardMapper boardMapper;
  private final FileService fileService;

  @Override
  public List<Board> list() throws Exception {
    List<Board> list = boardMapper.list();
    return list;
  }

  @Override
  public Board select(Integer no) throws Exception {
    Board board = boardMapper.select(no);
    return board;
  }

  @Override
  @Transactional
  public boolean insert(Board board) throws Exception {
    // 게시글 등록
    int result = boardMapper.insert(board);
    int parentNo = board.getNo();
    // 파일 등록
    int fileResult = fileService.upload(board.getFiles(), ParentTable.BOARD, parentNo);
    log.info("파일 업로드 - {}개 파일 등록", fileResult);
    return result > 0;
  }
  
  @Override
  @Transactional
  public boolean update(Board board) throws Exception {
    // 게시글 수정
    int result = boardMapper.update(board);
    // 파일 등록
    int parentNo = board.getNo();
    int fileResult = fileService.upload(board.getFiles(), ParentTable.BOARD, parentNo);
    log.info("파일 업로드 - {}개 파일 등록", fileResult);
    return result > 0;
  }
  
  @Override
  @Transactional
  public boolean delete(Integer no) throws Exception {
    // 게시글 삭제
    int result = boardMapper.delete(no);
    // 파일 삭제
    int fileResult = fileService.deleteByParent(ParentTable.BOARD, no);
    log.info("파일 삭제 - {}개 파일 삭제", fileResult);
    return result > 0;
  }


  
}
