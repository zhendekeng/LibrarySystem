package cn.zzy.library_web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.zzy.library_web.dao")
@SpringBootApplication
public class LibraryWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryWebApplication.class, args);
    }

}
