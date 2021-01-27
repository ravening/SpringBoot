package com.rakeshv.ifinances;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class IfinancesApplication {

    public static void main(String[] args) {
        SpringApplication.run(IfinancesApplication.class, args);
    }

}
