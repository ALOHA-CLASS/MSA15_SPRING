package com.aloha.product.dto;

import java.util.Date;
import java.util.UUID;

import org.apache.ibatis.type.Alias;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@Alias("Base")        // 별칭 (Mybatis package 생략용 - Mapper에서 사용)
public class Base {

    private Long no;                                    // PK
    @Builder.Default        
    private String id = UUID.randomUUID().toString();   // ID
    private Date createdAt;                             // 생성일
    private Date updatedAt;                             // 수정일

    public Base() {
        this.id = UUID.randomUUID().toString();         // UUID로 ID 생성
    }

    public Base(Long no, String id, Date createdAt, Date updatedAt) {
      this.no = no;
      this.id = (id != null) ? id : UUID.randomUUID().toString();
      this.createdAt = createdAt;
      this.updatedAt = updatedAt;
    }


}
