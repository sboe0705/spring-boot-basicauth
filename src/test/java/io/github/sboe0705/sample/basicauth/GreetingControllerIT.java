package io.github.sboe0705.sample.basicauth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GreetingControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGreetWithoutAuthentication() {
        // when
        String url = format("http://localhost:%s/api/greet", port);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // then
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    void testGreetWithUser() {
        // when
        String url = format("http://localhost:%s/api/greet", port);
        ResponseEntity<String> response = restTemplate.withBasicAuth("bob", "bob")
                .getForEntity(url, String.class);

        // then
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
    }

}
