Данный сервис представляет работу по созданию и управлению профиля пользователя.

Собрать проект нужно выполнить комманду 

mvn clean install -U 

Запустить сервис:

1. cd user-service-web
2. mvn clean spring-boot:run

База данных используется h2. Доступ до нее http://localhost:8080/UserService/h2-console

API сервиса можно использовать с помощью swagger по адрессу http://localhost:8080/UserService/swagger-ui.html