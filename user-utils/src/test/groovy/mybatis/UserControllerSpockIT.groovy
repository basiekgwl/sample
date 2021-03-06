package mybatis

import lombok.extern.slf4j.Slf4j
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpHeaders
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll
import utils.mybatis.SampleXmlApplication

@Slf4j
@ContextConfiguration
@SpringBootTest(classes = SampleXmlApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerSpockIT extends Specification {


    @LocalServerPort
    private int port

    public TestRestTemplate restTemplate = new TestRestTemplate()
    public HttpHeaders headers = new HttpHeaders()


    @Unroll
    def "should load all data"() {
        given: "one temperature entry"
        String testReq = "Ala"

        when: "loading data from repository"
        String test2 = "MaKota"

        then: "persisted data is loaded"
        (testReq + test2) == "AlaMaKota"
    }

//    private String createURLWithPort(String uri) {
//        return "http://localhost:" + port + uri;
//    }


}
