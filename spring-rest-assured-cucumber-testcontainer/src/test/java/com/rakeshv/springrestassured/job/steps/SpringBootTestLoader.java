package com.rakeshv.springrestassured.job.steps;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import javax.sql.DataSource;

/**
 * NOTE: BeforeAll abd AfterAll is from cucumber and not junit
 */
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {
                "spring.security.user.name=user",
                "spring.security.user.password=password",
                "spring.security.user.roles=ADMIN"
})
public class SpringBootTestLoader {

    static PostgreSQLContainer postgresContainer;

    @ServiceConnection
    static final PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:latest");

    @BeforeAll
    public static void setup() {
        postgreSQLContainer.withReuse(true).start();
    }

    @AfterAll
    public static void tearDown() {
        postgreSQLContainer.stop();
    }

//    @TestConfiguration
//    static class PostgresTestConfiguration {
//
//        @Bean
//        DataSource dataSource() {
//            System.out.println("starting DB");
//            postgresContainer = new PostgreSQLContainer(DockerImageName.parse("postgres:latest"))
//                    .withDatabaseName("bddfun")
//                    .withUsername("postgres")
//                    .withPassword("postgres");
//            postgresContainer.start();
//
//            HikariConfig hikariConfig = new HikariConfig();
//            hikariConfig.setJdbcUrl(postgresContainer.getJdbcUrl());
//            hikariConfig.setUsername(postgresContainer.getUsername());
//            hikariConfig.setPassword(postgresContainer.getPassword());
//            return new HikariDataSource(hikariConfig);
//        }
//    }
//
//    @AfterAll
//    public static void tearDown() {
//        System.out.println("closing DB connection");
//        postgresContainer.stop();
//    }

}
