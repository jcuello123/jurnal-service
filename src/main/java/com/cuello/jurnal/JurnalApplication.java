package com.cuello.jurnal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// TODO: remove this exclude when database is ready
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class JurnalApplication {

    public static void main(String[] args) {
        SpringApplication.run(JurnalApplication.class, args);
    }

}
