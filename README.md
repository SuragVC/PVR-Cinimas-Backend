<h1 align="center">PVR-Cinimas-Backend</h1>
<p>It's an imaginary backend application that mimics the working of PVR cinemas. It's an individual project in which I have worked with Java, SpringBoot, JPA, Hibernate, JWT Tokens, Spring Security, Maven, JUnit 5, and MySql as Backend.</p>
<h2>Features : </h2>
<p>-> Spring Security With JWT Authentication.</p>
<p>-> Functional Interface implemented.</p>
<p>-> Serilization and Deserilization for tickets permited to admin user.</p>
<p>-> Immutable object for saving PVR object.</p>
<p>-> JUnit testing for some API's.</p>
<p>-> Difrent API's for various purpose.</p>
</br>
<h2>Techstacks used : </h2>
<p>-> Java 17</p>
<p>-> MySQL database</p>
<p>-> Spring</p>
<p>-> SpringBoot</p>
<p>-> Spring MVC</p>
<p>-> JPA</p>
<p>-> Hibernate</p>
<p>-> Maven</p>
<p>-> Lombok</p>
<p>-> Swagger UI</p>
<p>-> JUnit 5</p>
<p>-> Java 8 features(Lambda expression, Functional interface,etc...</p>
</br>
<h2>Database authorization : </h2>
<p>
#changing the server port
server.port=8888

#db specific properties
spring.datasource.url=jdbc:mysql://localhost:3306/PVRCinimas
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=root

#ORM s/w specific properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.mvc.throw-exception-if-no-handler-found=true
spring.mvc.pathmatch.matching-strategy = ANT_PATH_MATCHER
logging.level.my.package.name=DEBUG</p>
