package com.rakeshv.springrestassured;

import com.rakeshv.springrestassured.job.core.JobService;
import io.restassured.RestAssured;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

//@SpringBootTest(webEnvironment = RANDOM_PORT)
//@Testcontainers
class SpringRestAssuredApplicationTests {

//    @Container
//    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(
            DockerImageName.parse("postgres:latest")
    );

//    @LocalServerPort
    private Integer port;

//    @Autowired
    JobService jobService;

//    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }
//    @Test
    void contextLoads() {
    }

}
