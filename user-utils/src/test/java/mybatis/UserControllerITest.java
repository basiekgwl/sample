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
import utils.mybatis.SampleXmlApplication;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleXmlApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerITest {

    @LocalServerPort
    private int port;

    public TestRestTemplate restTemplate = new TestRestTemplate();

    public HttpHeaders headers = new HttpHeaders();


    @Test
    public void testGetAccountNeg() throws JSONException {

        String accountNrb = "12345667";

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/user/getOneAccountAndUserData?accountNrb=" + accountNrb),
                HttpMethod.GET, entity, String.class);

        log.info("Response: " + response.getBody());

        String expected =
                "{\n" +
                        "\n" +
                        "    \"status\": \"ConstraintViolationException\",\n" +
                        "    \"statusHTTP\": \"BAD_REQUEST\",\n" +
                        "    \"error\": \"Bad Request\",\n" +
                        "    \"message\": \"[ConstraintViolationImpl{interpolatedMessage='Parameter 'accountNbr' should be 26 characters long', propertyPath=getOneAccountAndUserData.arg0, rootBeanClass=class utils.mybatis.controller.UserController, messageTemplate='Parameter 'accountNbr' should be 26 characters long'}]\",\n" +
                        "    \"path\": \"http://localhost:" + port + "/api/user/getOneAccountAndUserData\"\n" +
                        "\n" +
                        "}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void testGetUserByNamePos() throws JSONException {

        String name = "'Agnieszka Zawadzka'";

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/user/getUserByName?fullName=" + name),
                HttpMethod.GET, entity, String.class);

        log.info("Response: " + response.getBody());

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

    @Test
    public void testGetUserByNik() throws JSONException {

        String nik = "23241122";

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/user/getUserByNik?nik=" + nik),
                HttpMethod.GET, entity, String.class);

        log.info("Response: " + response.getBody());

        String expected =
                "{\n" +
                        "\n" +
                        "    \"nik\": \"23241122\",\n" +
                        "    \"fullName\": \"Harry Potter\",\n" +
                        "    \"nip\": \"4231235001\",\n" +
                        "    \"pesel\": \"94021200333\",\n" +
                        "    \"address\": \"ul. Karmelicka 12/3, 31-820 Kraków\"\n" +
                        "\n" +
                        "}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void getAccountByNrb() throws JSONException {

        String nrb = "26200578901234567890120000";

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("api/accounts/getAccountByNrb?nrb=" + nrb),
                HttpMethod.GET, entity, String.class);

        log.info("Response: " + response.getBody());

        String expected =
                "{\n" +
                        "\n" +
                        "    \"nrb\": \"26200578901234567890120000\",\n" +
                        "    \"accountType\": \"SAVINGS\",\n" +
                        "    \"balance\": 10322.99\n" +
                        "\n" +
                        "}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void testGetAccountWithOwner() throws JSONException {

        String accountNrb = "26005678901234567890123456";

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/user/getOneAccountAndUserData?accountNrb=" + accountNrb),
                HttpMethod.GET, entity, String.class);

        log.info("Response: " + response.getBody());

        String expected =
                "{\n" +
                        "\n" +
                        "    \"userDto\": {\n" +
                        "        \"nik\": \"44332211\",\n" +
                        "        \"fullName\": \"Dorota Kowalczyk\",\n" +
                        "        \"nip\": \"5531819323\",\n" +
                        "        \"pesel\": \"85102344333\",\n" +
                        "        \"address\": \"ul. Wiśniowa 15, 34-350 Węgierska Górka\"\n" +
                        "    },\n" +
                        "    \"nrb\": \"26005678901234567890123456\",\n" +
                        "    \"accountType\": \"SAVINGS\",\n" +
                        "    \"balance\": 40322.99\n" +
                        "\n" +
                        "}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
