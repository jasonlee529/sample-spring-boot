package cn.lee.jason.springcloudeurake.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@FeignClient(name = "SERVICE-provider")
public interface FeignService {

    @GetMapping("user/{id}")
    public Map findOne(@PathVariable long id);


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello();

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public List<String> info();
}
