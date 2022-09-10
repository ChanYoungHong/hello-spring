package springbootbasic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class HelloController {

    @GetMapping( "/hello2")
    public String getSomething() {

        return "Hello world Happy 추석";
    }

    @GetMapping(value = "/request1")
    public String getRequestParam(
        @RequestParam String name,
        @RequestParam String email,
        @RequestParam String organization
    ) {

        return name + "" + email + "" + organization;
    }

}
