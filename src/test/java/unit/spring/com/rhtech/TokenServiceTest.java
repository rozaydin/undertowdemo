package unit.spring.com.rhtech;

import org.junit.jupiter.api.Test;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.InvalidSignatureException;
import org.springframework.security.jwt.crypto.sign.MacSigner;

import java.util.Map;

public class TokenServiceTest {

    @Test
    public void validateJwtToken() {

        /**
         * {
         *   "iss": "rubic-cube",
         *   "iat": 1508159483,
         *   "exp": 1539695485,
         *   "aud": "MindSphere AS",
         *   "sub": "rubic-cube",
         *   "nbf": "1508159483",
         *   "schemas": "[\"urn:siemens:mindsphere:v1\"]",
         *   "ten": "tenant12345"
         *  }
         *
         *  Shared Secret: secret
         */

        String sharedSecret = "secret";
        String jwtString = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJydWJpYy1jdWJlIiwiaWF0IjoxNTA4MTU5NDgzLCJleHAiOjE1Mzk2OTU0ODUsImF1ZCI6Ik1pbmRTcGhlcmUgQVMiLCJzdWIiOiJydWJpYy1jdWJlIiwibmJmIjoiMTUwODE1OTQ4MyIsInNjaGVtYXMiOiJbXCJ1cm46c2llbWVuczptaW5kc3BoZXJlOnYxXCJdIiwidGVuIjoidGVuYW50MTIzNDUifQ.9C_ex6VcqULbhbANDSaJndq-zdnVi_k6EqnYAlN9kzM";
        // JWTHeader[] mandatoryJWTHeaders = JWTUtil.getMandatoryJWTHeaders();
        // boolean verificationResult = JWTUtil.checkMandatoryHeaders(jwtString, mandatoryJWTHeaders);

        // default algorithm is HMACSHA256
        try {

            Jwt jwt = JwtHelper.decode(jwtString);
            Map<String, String> headers = JwtHelper.headers(jwtString);
            String algorithm = headers.get("alg");
            switch (algorithm) {

                case "":
                    break;
                default:
                    throw new UnsupportedOperationException("Provided algorithm is not supported " + algorithm);
                    // not supported yet

            }

            // Jwt jwt = JwtHelper.decodeAndVerify(jwtString, new MacSigner("secret"));
        } catch (InvalidSignatureException ise) {
            // log exception
            throw ise;
        }

    }

}
