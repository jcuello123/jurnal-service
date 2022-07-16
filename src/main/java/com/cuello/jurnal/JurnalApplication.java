package com.cuello.jurnal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class JurnalApplication {

    public static void main(String[] args) {
        SpringApplication.run(JurnalApplication.class, args);
    }

}
