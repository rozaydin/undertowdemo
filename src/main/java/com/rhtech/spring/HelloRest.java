package com.rhtech.spring;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloRest {

    @GetMapping()
    public String getPerson() {
        return "cant believe this actually works";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity grantToken(@RequestParam("grant_type") final String grantType,
                                     @RequestParam("client_assertion_type") final String clientAssertionType,
                                     @RequestParam("client_assertion") final String clientAssertion) {

        //
        System.out.println(grantType);
        System.out.println(clientAssertionType);
        System.out.println(clientAssertion);

        return new ResponseEntity(HttpStatus.OK);
    }

}
