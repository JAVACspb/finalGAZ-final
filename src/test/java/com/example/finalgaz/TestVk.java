package com.example.finalgaz;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;


public class TestVk extends AbstractTest{


ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void firstTest() throws JsonProcessingException {
        Request request = new Request("561468586","ufc");

        configureFor("localhost", 8082);
        stubFor(post(urlPathMatching("/method/groups.isMember"))
                .withHeader("vk_service_token",equalTo("token"))
                .willReturn(ok()
                        .withHeader("Content-Type", "text/plain")
                        .withBody("NOT_FOUND")
                )

        );

        given()
                .body(objectMapper.writeValueAsString(request))
                .header("vk_service_token","token")
                .post("/api/vk")
                .then()
                .assertThat()
                .statusCode(404);

    }
    @Test
    public void secondTest() throws JsonProcessingException {
        Request request = new Request("561468586","ullfddetfhhvhggfc");

        configureFor("localhost", 8082);
        stubFor(post(urlPathMatching("/method/users.get"))
                .withHeader("vk_service_token",equalTo("token"))
                .willReturn(badRequest()
                        .withHeader("Content-Type", "text/plain")
                        .withBody("NOT_FOUND")
                )
        );

        given()
                .body(objectMapper.writeValueAsString(request))
                .header("vk_service_token","token")
                .post("/api/vk")
                .then()
                .assertThat()
                .statusCode(404);

    }


}
