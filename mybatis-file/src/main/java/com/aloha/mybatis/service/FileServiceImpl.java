package com.aloha.mybatis.service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.aloha.mybatis.dto.Files;
import com.aloha.mybatis.dto.ParentTable;
import com.aloha.mybatis.mapper.FileMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

  private final FileMapper fileMapper;

  @Value("${spring.servlet.multipart.location}")
  private String uploadPath;

  @Override
  public List<Files> list() throws Exception {
    List<Files> files = fileMapper.list();
    return files;
  }

  @Override
  public Files select(Integer no) throws Exception {
    Files file = fileMapper.select(no);
    return file;
  }

  @Override
  public Files selectById(String id) throws Exception {
    Files file = fileMapper.selectById(id);
    return file;
  }

  @Override
  public boolean insert(Files file) throws Exception {
    int result = fileMapper.insert(file);
    return result > 0;
  }

  @Override
  public boolean update(Files file) throws Exception {
    int result = fileMapper.update(file);
    return result > 0;
  }

  @Override
  public boolean updateById(Files file) throws Exception {
    int result = fileMapper.updateById(file);
    return result > 0;
  }

  @Override
  public boolean delete(Integer no) throws Exception {
    Files file = fileMapper.select(no);
    // 파일 삭제
    File realFile = new File(file.getPath());
    if (realFile.exists()) {
      realFile.delete();
    }
    // DB 삭제
    int result = fileMapper.delete(no);
    return result > 0;
  }

  @Override
  public boolean deleteById(String id) throws Exception {
    Files file = fileMapper.selectById(id);
    // 파일 삭제
    File realFile = new File(file.getPath());
    if (realFile.exists()) {
      realFile.delete();
    }
    // DB 삭제
    int result = fileMapper.deleteById(id);
    return result > 0;
  }

  @Override
  public int upload(List<MultipartFile> files, ParentTable parentTable, Integer parentNo) throws Exception {
    int sortOrder = 0;
    if (files != null) {
      for (MultipartFile file : files) {
        Files newFile = new Files();
        String fileName = file.getOriginalFilename();
        String path = uploadPath + UUID.randomUUID().toString() + "_" + fileName;

        // 파일 저장
        File realFile = new File(path);
        byte[] fileData = file.getBytes();
        FileCopyUtils.copy(fileData, realFile);

        // DB 저장
        // 컨텐츠 타입 - 확장자로 추출
        newFile.setParentNo(parentNo);
        newFile.setParentTable(parentTable.value());
        newFile.setName(fileName);
        newFile.setPath(path);
        newFile.setSize(file.getSize());
        newFile.setContentType(file.getContentType());
        newFile.setSortOrder(sortOrder++);
        if( sortOrder == 1)
          newFile.setIsMain(true);
        fileMapper.insert(newFile);
      }
    }
    return sortOrder;
  }

  @Override
  public List<Files> listByParent(ParentTable parentTable, Integer parentNo) throws Exception {
    
    // List<Files> fileList = fileMapper.listByParent(parentTable.value(), parentNo);
    
    Files files = new Files();
    files.setParentTable(parentTable.value());
    files.setParentNo(parentNo);
    List<Files> fileList = fileMapper.listByParent(files);
    return fileList;
  }

  @Override
  public List<Files> listByParent(Files files) throws Exception {
    List<Files> fileList = fileMapper.listByParent(files);
    return fileList;
  }

  @Override
  public int deleteByParent(ParentTable parentTable, Integer parentNo) throws Exception {
    // 파일 목록 조회
    List<Files> fileList = listByParent(ParentTable.BOARD, parentNo);
    // 파일 삭제
    for (Files file : fileList) {
      File realFile = new File(file.getPath());
      if (realFile.exists()) {
        realFile.delete();
      }
    }
    // DB 삭제
    // int result = fileMapper.deleteByParent(parentTable, parentNo);
    Files files = new Files();
    files.setParentTable(parentTable.value());
    files.setParentNo(parentNo);
    int result = fileMapper.deleteByParent(files);
    return result;
  }

  @Override
  public int deleteByParent(Files files) throws Exception {
    // 파일 목록 조회
    List<Files> fileList = listByParent(files);
    // 파일 삭제
    for (Files file : fileList) {
      File realFile = new File(file.getPath());
      if (realFile.exists()) {
        realFile.delete();
      }
    }
    // DB 삭제
    int result = fileMapper.deleteByParent(files);
    return result;
  }

  @Override
  public boolean updateSortOrder(List<Map<String, Object>> sortOrderList) throws Exception {
    int index = 0;
    int result = 0;
    for (Map<String,Object> map : sortOrderList) {
      String id = (String) map.get("key");

      Files file = new Files();
      file.setId(id);
      file.setSortOrder(index++);
      result += fileMapper.updateById(file);
    }
    return result == sortOrderList.size();
  }

  

  
}
