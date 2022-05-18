package com.zj.resourceprojects;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zj.resourceprojects.Mapper")
public class ResourceProjectsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceProjectsApplication.class, args);
    }

}
