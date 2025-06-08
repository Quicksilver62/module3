package ru.yandex.practicum.servicea;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureStubRunner(
        ids = "ru.yandex.practicum:serviceB:+:stubs:8080",
        stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
public class ServiceAContractTest {

    @LocalServerPort
    int port;

    @Test
    void getData_shouldReturnMessageFromB() {
        String response = new RestTemplate().getForObject(
                "http://localhost:" + port + "/get-data", String.class);

        assertTrue(response.contains("Hello from B"));
    }
}
