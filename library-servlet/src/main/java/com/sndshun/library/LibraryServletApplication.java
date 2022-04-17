package com.sndshun.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author mr.sun
 * 开启事物
 */
@EnableTransactionManagement
@SpringBootApplication
public class LibraryServletApplication {
    public static void main(String[] args) {
        SpringApplication.run(LibraryServletApplication.class, args);
    }

}
