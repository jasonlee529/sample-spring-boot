package cn.lee.jason.springcloudeurake.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        return "Hello World";
    }

    @GetMapping(value = "/info")
    public List<String> info() {
        List<String> result = new ArrayList<>();
        List<String> ids = client.getServices();
        for (String id : ids) {
            List<ServiceInstance> instances = client.getInstances(id);
            for(ServiceInstance instance : instances){
                result.add("/info,URL : " + instance.getUri() + ",service_id : " + instance.getServiceId());
            }
        }
        return result;
    }
}

