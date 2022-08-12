package hello.hellospring.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JWTUtilTest {

    private JWTUtil jwtUtil;

    @BeforeEach
    public void testBefore() {

        System.out.println("testBefore...................");
        jwtUtil = new JWTUtil();
    }

    @Test
    public void testEncode() throws Exception {
        String email = "user95@naver.com";

        String str = jwtUtil.generateToken(email);

        System.out.println(str);
    }

    @Test
    public void testValidate() throws Exception {

        String email = "user95@naver.com";

        String str = jwtUtil.generateToken(email);

        Thread.sleep(5000);

        String resultEmail = jwtUtil.validateAndExtract(str);

        System.out.println(resultEmail);

    }

}