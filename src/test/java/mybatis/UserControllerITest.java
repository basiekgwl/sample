package mybatis;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleXmlApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerITest {

    @LocalServerPort
    private int port;

    public TestRestTemplate restTemplate = new TestRestTemplate();

    public HttpHeaders headers = new HttpHeaders();


    @Test
    public void testRetrieveStudentCourse() throws JSONException {

        String accountNrb = "";

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/user/getOneAccountAndUserData?" + accountNrb),
                HttpMethod.GET, entity, String.class);

        log.info("Response: " + response.getBody());

        log.info("Entity: " + entity);

        String expected =
                "{\"status\":\"400\"," +
                        "\"statusHTTP\":\"BAD_REQUEST\"," +
                        "\"error\":\"Bad Request\"," +
                        "\"exception\":\"org.springframework.web.bind.MissingServletRequestParameterException\"," +
                        "\"message\":\"Required String parameter 'accountNrb' is not present\"," +
                        "\"customMessage\":\"[Missing Parameter]\"," +
                        "\"path\":\"http://localhost:" + port + "/user/getOneAccountAndUserData\"}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }


}
