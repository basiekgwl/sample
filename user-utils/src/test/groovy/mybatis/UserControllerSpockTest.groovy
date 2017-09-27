package mybatis

import lombok.extern.slf4j.Slf4j
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll
import utils.mybatis.SampleXmlApplication

@Slf4j
@ContextConfiguration
@SpringBootTest(classes = SampleXmlApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerSpockTest extends Specification {


    @LocalServerPort
    private int port

    public TestRestTemplate restTemplate = new TestRestTemplate()
    public HttpHeaders headers = new HttpHeaders()


    @Unroll
    def "should load all data"() {
        given: "set preconditions"
        String testReq = "Ala"
        String nik = "23241122"

        HttpEntity<String> entity = new HttpEntity<String>(null, headers)
        String expected = TestDataRespList.RESP_FIRST

        when: "Send requst"
        String test2 = "MaKota"


        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort(TestDataRespList.GET_USER_BY_NIK + nik),
                HttpMethod.GET, entity, String.class)


        then: "verify response"
        (testReq + test2) == "AlaMaKota"

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

//    private String createURLWithPort(String uri) {
//        return "http://localhost:" + port + uri;
//    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri
    }

}
