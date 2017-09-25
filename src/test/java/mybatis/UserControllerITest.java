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

        String accountNrb = "12345667";

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/user/getOneAccountAndUserData?accountNrb=" + accountNrb),
                HttpMethod.GET, entity, String.class);

        log.info("Response: " + response.getBody());

        String expected =
                "{\n" +
                        "    \"status\": \"ConstraintViolationException\",\n" +
                        "    \"statusHTTP\": \"BAD_REQUEST\",\n" +
                        "    \"error\": \"Bad Request\",\n" +
                        "    \"message\": \"[ConstraintViolationImpl{interpolatedMessage='Parameter 'accountNbr' should be 26 characters long', propertyPath=getOneAccountAndUserData.arg0, rootBeanClass=class mybatis.controller.UserController, messageTemplate='Parameter 'accountNbr' should be 26 characters long'}]\",\n" +
                        "    \"path\": \"http://localhost:" + port + "/user/getOneAccountAndUserData\"\n" +
                        "}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void testPositiveGetUser() throws JSONException {

        String name = "'Agnieszka Zawadzka'";

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/user/getUserByName?fullName=" + name),
                HttpMethod.GET, entity, String.class);

        log.info("Response: " + response.getBody());

        log.info("Entity: " + entity);

        String expected =
                "[\n" +
                        "    {\n" +
                        "        \"nik\": \"24113114\",\n" +
                        "        \"fullName\": \"Agnieszka Zawadzka\",\n" +
                        "        \"nip\": \"4231235412\",\n" +
                        "        \"pesel\": \"94021233333\",\n" +
                        "        \"address\": \"ul. Nowa 20, 33-230 Katowice\"\n" +
                        "    }\n" +
                        "]";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
