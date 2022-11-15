package com.example.finalgaz;


import com.github.tomakehurst.wiremock.WireMockServer;

public class AbstractTest {
    WireMockServer wireMockServer = new WireMockServer(8082);

    {
        wireMockServer.start();
    }



}
