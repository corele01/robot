FROM corele/docker-jdk8:0.0.2
WORKDIR /app
COPY . /app
EXPOSE 8080
CMD ["java","-jar","/app/robot.jar"]