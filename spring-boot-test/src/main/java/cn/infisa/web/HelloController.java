package cn.infisa.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "hello")
public class HelloController {

    @RequestMapping(value = "")
    public String hello() {
        return "hello";
    }
}
