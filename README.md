# cleverClientsData

# Технологии
<ul>
<li>Spring MVC, Security</li>
<li>Hibernate</li>
<li>MySQL</li>
<li>Maven</li>
<li>Git</li>
<li>Tomcat</li>
<li>Nginx</li>
</ul>

# Суть проекта:
Операторы вбивают анкету клиента в БД.
Потом рекламщики централизованно забирают данные, куда им надо.

# Стартовый скрипт БД:
<pre>
/*All User's gets stored in APP_USER table*/
create table APP_USER (
   id BIGINT NOT NULL AUTO_INCREMENT,
   USER_NAME VARCHAR(30) NOT NULL,
   PASSWORD VARCHAR(100) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (USER_NAME)
);
   
/* USER_PROFILE table contains all possible roles */
create table USER_PROFILE(
   id BIGINT NOT NULL AUTO_INCREMENT,
   type VARCHAR(30) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (type)
);

/* JOIN TABLE for MANY-TO-MANY relationship*/  
CREATE TABLE APP_USER_USER_PROFILE (
    user_id BIGINT NOT NULL,
    user_profile_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, user_profile_id),
    CONSTRAINT FK_APP_USER FOREIGN KEY (user_id) REFERENCES APP_USER (id),
    CONSTRAINT FK_USER_PROFILE FOREIGN KEY (user_profile_id) REFERENCES USER_PROFILE (id)
);
  
/* Populate USER_PROFILE Table */
INSERT INTO USER_PROFILE(type)
VALUES ('USER');
  
INSERT INTO USER_PROFILE(type)
VALUES ('ADMIN');
  
INSERT INTO USER_PROFILE(type)
VALUES ('DBA');
  
INSERT INTO USER_PROFILE(type)
VALUES ('LOGIN');

  
/* Populate one Admin User which will further create other users for the application using GUI */
INSERT INTO APP_USER(USER_NAME, password)
VALUES ('admin','$10$al/Ew06.j16agrGcb3uUhuPv0MQhhXiwOcR9K8MlfV.AcHpCpcRbO');
    
/* Populate JOIN Table */
INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id)
  SELECT user.id, profile.id FROM app_user user, user_profile profile
  where user.USER_NAME='admin' and profile.type='ADMIN';
 
/* Create persistent_logins Table used to store rememberme related stuff*/
/* 
login: admin
passwd: 111
*/
CREATE TABLE persistent_logins (
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) NOT NULL,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
);
</pre>

# Источники информации
<ul>
<li>http://materializecss.com/
<li>http://websystique.com/springmvc/spring-mvc-4-and-spring-security-4-integration-example/
<li>http://ru.stackoverflow.com/
<li>http://google.com/
</ul>