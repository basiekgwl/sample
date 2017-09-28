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
    def "POS - getUserByNik"() {
        given: "set preconditions"
        HttpEntity<String> entity = new HttpEntity<String>(null, headers)

        when: "Send requst"
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort(uri + nik),
                HttpMethod.GET, entity, String.class)


        then: "verify response"
        JSONAssert.assertEquals(expectedResp, response.getBody(), false)

        where:
        nik        | uri                              | expectedResp
        "23241122" | TestDataRespList.GET_USER_BY_NIK | TestDataRespList.RESP_FIRST
        "23241534" | TestDataRespList.GET_USER_BY_NIK | TestDataRespList.RESP_SECOND
    }


    @Unroll
    def "NEG - getUserByNik"() {

        given: "set preconditions"
        HttpEntity<String> entity = new HttpEntity<String>(null, headers)


        when: "Send requst"
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort(uri + nik),
                HttpMethod.GET, entity, String.class)


        then: "verify response"
        response.getBody().contains(expectedResp)


        where:
        nik         | uri                              | expectedResp
        null        | TestDataRespList.GET_USER_BY_NIK | TestDataRespList.RESP_EXC_FIRST
        "12345"     | TestDataRespList.GET_USER_BY_NIK | TestDataRespList.RESP_EXC_FIRST
        "123456333" | TestDataRespList.GET_USER_BY_NIK | TestDataRespList.RESP_EXC_FIRST
        "23241533"  | TestDataRespList.GET_USER_BY_NIK | TestDataRespList.RESP_EXC_SECOND
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri
    }

}
