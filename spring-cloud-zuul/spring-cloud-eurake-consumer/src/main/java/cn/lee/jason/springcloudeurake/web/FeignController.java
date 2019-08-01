package cn.lee.jason.springcloudeurake.web;

import cn.lee.jason.springcloudeurake.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "feign")
public class FeignController {
    @Autowired
    private FeignService feignService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String helloConsumer() {
        return feignService.hello();
    }

    @GetMapping(value = "info")
    public List<String> info() {
        return feignService.info();
    }

    @GetMapping(value = "user/{id}")
    public Map user(@PathVariable long id , HttpServletRequest request) {
        return feignService.findOne(id);
    }
}
