package cn.lee.jason.springcloudeurake.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RibbonService {
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

    public Map findOne(long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        URI uri = UriComponentsBuilder.fromUriString("http://SERVICE-Provider/user/{id}")
                .build().expand(params).encode().toUri();
        return restTemplate.getForEntity(uri, Map.class).getBody();
    }
}
