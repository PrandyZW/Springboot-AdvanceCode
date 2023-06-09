package com.yzw.advance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ServletComponentScan
@ComponentScan(basePackages = "com.yzw")
public class AdvanceCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdvanceCodeApplication.class, args);
    }

}
