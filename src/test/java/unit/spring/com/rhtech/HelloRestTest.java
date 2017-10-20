package unit.spring.com.rhtech;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.assertj.core.api.Assertions.*;

public class HelloRestTest {

    @Test
    public void testAll() {

        MockHttpServletRequest request = new MockHttpServletRequest(HttpMethod.GET.name(), "/token");
        request.setContentType("application/x-www-form-urlencoded");
        request.addHeader("","");
    }

}
