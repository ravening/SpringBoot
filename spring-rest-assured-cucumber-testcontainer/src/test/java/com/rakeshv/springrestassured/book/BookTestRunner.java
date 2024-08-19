package com.rakeshv.springrestassured.book;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/book/features",
        glue = {"com.rakeshv.springrestassured.book.steps"},
        plugin = {"pretty", "html:target/cucumber-reports/index.html",
                            "json:target/cucumber-reports/cucumber.json" }
)
public class BookTestRunner {
}
