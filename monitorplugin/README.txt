Giới thiệu

Bluesea Monitor Plugin cung cấp thư viện dùng để thông tin (cảnh báo) ra Monitor Client.

Sử dụng RabbitMQ (http://www.rabbitmq.com/) làm queue và Spring Framework 3.0.7 (http://www.springsource.org/get-started).

Có 3 mức cảnh báo ở 3 Level. Tương tự như dùng log4j

+ INFO: Nên dùng khi muốn hiển thị thông tin dịch vụ.

+ DEBUG: Dùng khi muốn thông tin chi tiết xử lý dịch vụ

+ ERROR: Dùng để đưa ra thông báo lỗi của dịch.

Tích hợp log4j với RabbitMQ sử dụng Spring AMQP (http://www.springsource.org/spring-amqp) để log thông tin ra Monitor Client.