-- Active: 1765956701887@@127.0.0.1@3306@aloha
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `no` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'PK',
  `id` varchar(64) NOT NULL UNIQUE COMMENT 'UK',
  `name` varchar(100) NOT NULL COMMENT '상품명',
  `price` int NOT NULL COMMENT '가격',
  `stock` int NOT NULL COMMENT '재고수량',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자'
) COMMENT '상품 테이블';

-- 샘플 데이터
INSERT INTO `product` (`id`, `name`, `price`, `stock`) VALUES
('P001', '상품1', 10000, 50),
('P002', '상품2', 20000, 30),
('P003', '상품3', 15000, 20);