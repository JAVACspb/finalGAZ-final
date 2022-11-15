package com.example.finalgaz;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.Test;

import javax.xml.transform.Result;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.mockito.BDDMockito.given;

public class TestVk extends AbstractTest{



    @Test
    public void firstTest(){
//        wireMockServer.stub("/vk/user.getid")
        configureFor("localhost", 8082);
        stubFor(get("\"https://api.vk.com/method/groups.isMember?bridgeEndpoint=true&\" +\n" +
                "        \"user_id=561468586&group_id=breed_dog_help&\" +\n" +
                "        \"access_token=vk1.a.YhVsQ9Wmc-3Wp-vs0CupiJTu-9dX5jW-BjkjVVFZl3dHwTVmHu6gfkypromLIXaEd6NlQwFgQ8FqFK7rGML1vibFK7U4cFSX0CHxGlo1ZO-\" +\n" +
                "        \"81RN4L7QsWNZfYaw9SHzfPyxnaaqxQVSgdE9_-ukZ0MRRiQ51RRRFeZqEx8dUX09ZllvWQzYqXNHFxQmK84Oa&v=5.131\"")
                .withHeader("Content-Type", containing("xml"))
                .willReturn(ok()
                        .withHeader("Content-Type", "text/xml")
                        .withBody("<response>SUCCESS</response>")));

        Result result = myHttpServiceCallingObject.doSomething();
        assertTrue(result.wasSuccessful());

        verify(postRequestedFor(urlPathEqualTo("/my/resource"))
                .withRequestBody(matching(".*message-1234.*"))
                .withHeader("Content-Type", equalTo("text/xml")));



        //можно указать с какими параметрами пришел запрос
        //вернуть какое либо бади


        //здесть отправлять запрос на localhost:8080/webjars/swagger-ui/vk...
//        assertThat(testClient.get("/some/thing").statusCode(), is(200));


    }
}
