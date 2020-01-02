package org.iptime.hoonyhoony.simple_board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //create | update date check
public class SimpleBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleBoardApplication.class, args);
    }

}

