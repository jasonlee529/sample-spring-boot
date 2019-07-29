package cn.lee.jason.springcloudeurake.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class HelloService {
    @Autowired
    private RestTemplate restTemplate;

    public String sayHello() {
        return restTemplate.getForEntity("http://SERVICE-provider/hello", String.class).getBody();
    }

    public String helloFallback() {
        return "error";
    }

    public List<String> info() {
        return restTemplate.getForEntity("http://SERVICE-provider/info", List.class).getBody();
    }
}
