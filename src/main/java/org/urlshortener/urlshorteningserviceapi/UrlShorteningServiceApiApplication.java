package org.urlshortener.urlshorteningserviceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class UrlShorteningServiceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlShorteningServiceApiApplication.class, args);
    }

}
