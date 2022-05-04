package com.sndshun.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author mr.sun
 * 开启事物
 */
@EnableTransactionManagement
@SpringBootApplication
@ImportResource(locations={"classpath:spring-*.xml"})
public class LibraryServletApplication {
    public static void main(String[] args) {
        SpringApplication.run(LibraryServletApplication.class, args);
    }

}
