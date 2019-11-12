package com.tet.fleamarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.tet.fleamarket.dao", "com.tet.fleamarket.entity"})
public class FleamarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(FleamarketApplication.class, args);
    }

}
