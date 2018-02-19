FROM tomcat:alpine

CMD ["catalina.sh", "run"]


EXPOSE 8080
#RUN ln -sf /usr/share/zoneinfo/Asia/Omsk /etc/localtime
ENV TZ=Asia/Omsk
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
COPY target/cleverQ-1.0.war /usr/local/tomcat/webapps/cleverQ.war
RUN mkdir -p /usr/local/tomcat/webapps/cleverQ/WEB-INF/classes
#RUN cp /srv/cleverQ/config/jdbc.properties /usr/local/tomcat/webapps/cleverQ/WEB-INF/classes


