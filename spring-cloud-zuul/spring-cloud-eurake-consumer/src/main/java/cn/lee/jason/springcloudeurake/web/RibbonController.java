package cn.lee.jason.springcloudeurake.web;

import cn.lee.jason.springcloudeurake.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "ribbon")
public class RibbonController {
    @Autowired
    private RibbonService ribbonService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String helloConsumer() {
        return ribbonService.sayHello();
    }

    @GetMapping(value = "info")
    public List<String> info() {
        return ribbonService.info();
    }
    @GetMapping(value = "user/{id}")
    public Map user(@PathVariable long id) {
        return ribbonService.findOne(id);
    }
}
