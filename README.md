# sicredi-test-java

API [Spring Boot](http://projects.spring.io/spring-boot/).

## Description

API to creation agenda's and open sessions to vote. Have integration with CPF validation using [FeignClient](https://cloud.spring.io/spring-cloud-openfeign/reference/html/). The solution use Spring-boot, Gradle, Junit and MongoDB to NoSQL Database.

## Requirements

For building and running the application you need:

- [JDK 11](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html)
- [Gradle ](https://gradle.org/)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `br.com.sicredi.SicrediTestJavaApplication` class from your IDE.


## Connecting to MongoDB

There is already a connection to the MongoDB cluster online, just change the application.yaml settings to your preferred settings.

```shell
spring:
  data:
    mongodb:
      uri: <your_uri>
```

## Questions

Have questions? Send-me e-mail: marconi.motta@hotmail.com
