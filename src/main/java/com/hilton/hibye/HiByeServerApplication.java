package com.hilton.hibye;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HiByeServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HiByeServerApplication.class, args);
    }

}
