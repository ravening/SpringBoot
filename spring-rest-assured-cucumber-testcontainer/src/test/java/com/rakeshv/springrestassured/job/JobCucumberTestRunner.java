package com.rakeshv.springrestassured.job;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import jakarta.annotation.PostConstruct;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.testcontainers.junit.jupiter.Testcontainers;

//@RunWith(Cucumber.class)
//@CucumberOptions(
//        features = "src/test/resources/job/features",
//        glue = {"com.spra.springrestassured.job.steps"},
//        plugin = {"pretty", "html:target/cucumber-reports/index.html",
//                "json:target/cucumber-reports/cucumber.json" }
//)
public class JobCucumberTestRunner {
}
