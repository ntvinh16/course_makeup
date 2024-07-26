-- Thêm cột mới CATEGORY_CODE và PARENT_CATEGORY_CODE
ALTER TABLE CATEGORIES
ADD COLUMN CATEGORY_CODE VARCHAR(50) UNIQUE,
ADD COLUMN PARENT_CATEGORY_CODE VARCHAR(50);

-- Đổi tên cột DESCRIPTION thành CONTENT
ALTER TABLE CATEGORIES
RENAME COLUMN DESCRIPTION TO CONTENT;

-- Tạo khóa ngoại cho cột parent_category_code
ALTER TABLE CATEGORIES
ADD CONSTRAINT fk_parent_category
FOREIGN KEY (PARENT_CATEGORY_CODE) REFERENCES CATEGORIES (CATEGORY_CODE);





