# book_commerce
# Tổng quan công nghệ
- **Java Spring Web**
  + Spring MVC (Model-View-Controller):
     - Model: Các lớp đối tượng thực thể ánh xạ cơ sỏ dữ liệu
     - Controller: Xử lý yêu cầu từ người dùng và quyết định cách phản hồi
     - View: Giao diện người dùng hiển thị dữ liệu được cung cấp bởi Model
   + RESTful Web Services:
     - Spring Web hỗ trợ phát triển RESTful Web Services thông qua Spring MVC
     - Sử dụng annotation đơn giản hóa việc tạo các RESTful endpoint
   + Thymeleaf (Template Engines):
     - Hỗ trợ template engines như Thymeleaf để tạo giao diện người dùng
     - Tích hợp với Spring MVC các tính năng thực hiện điều kiện, vòng lặp và biểu thức
   + Spring Security: cung cấp các tính năng xác thực và phân quyền
- **Spring Data JPA**
   + JPA và ORM:
     - JPA là một API của Java để tương tác với cơ sở dữ liệu
     - Thực hiện ánh xạ giữa các lớp đối tượng Java và bảng trong cơ sở dữ liệu
   + Repository Pattern: các phương thức tiêu biểu cho các thao tác cơ bản với cơ sở dữ liệu được tự động tạo ra dựa vào tên của phương thức
   + Quản Lý Truy Vấn:
     - Các quy ước đặt tên phương thức để tạo ra các câu truy vấn cơ sở dữ liệu mà không cần phải viết truy vấn SQL chi tiết
     - Annotation @Query để viết truy vấn tùy chỉnh nếu cần khi quy ước tên quá dài
   + Hỗ trợ nhiều loại cơ sở dữ liệu quan hệ thông qua tích hợp các implementation JPA khác nhau như Hibernate, EclipseLink và OpenJPA
   + Tích hợp dễ dàng với Spring Boot, giúp tự động cấu hình và cung cấp các tùy chọn mặc định cho các cài đặt JPA
- **Lombok**
   + Annotation-Based Code Generation: sử dụng các annotation để sinh mã nguồn tự động cho các phương thức getter, setter, constructor, equals, hashCode, và toString
   + Sử dụng annotation @Getter và @Setter để tự động tạo phương thức getter và setter cho các thuộc tính trong lớp đối tượng
   + Sử dụng annotation @NoArgsConstructor, @RequiredArgsConstructor, và @AllArgsConstructor để tự động tạo constructor mặc định, constructor cho thuộc tính bắt buộc và constructor với tất cả các thuộc tính
# Cấu trúc hệ thống
- ![s1](https://github.com/hungng7/book_commerce/assets/147014939/a0186753-f3b5-496c-baba-6047f5efe7f5)
- ![s2](https://github.com/hungng7/book_commerce/assets/147014939/5ca63ee7-2ba1-4093-abca-454dc18a6aed)
- ![s3](https://github.com/hungng7/book_commerce/assets/147014939/20f8f2de-9f5a-473b-ae0e-5f4842fc32a0)
