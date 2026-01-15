-- Active: 1765956701887@@127.0.0.1@3306@aloha
DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `no` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'PK',
  `id` VARCHAR(64) NOT NULL UNIQUE COMMENT 'UK',
  `name` VARCHAR(100) NOT NULL COMMENT '상품명',
  `price` INT NOT NULL COMMENT '가격',
  `stock` INT NOT NULL COMMENT '재고',
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP 
                                  ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시'
) COMMENT '상품'

-- 샘플 데이터
INSERT INTO `product` (`id`, `name`, `price`, `stock`)
VALUES 
(UUID(), '상품A', 10000, 50),
(UUID(), '상품B', 20000, 30),
(UUID(), '상품C', 15000, 20);