package cn.lee.jason.springcloudeurake.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("{id}")
    public Map findOne(@PathVariable long id, HttpServletRequest request) {
        Map map = new HashMap();
        map.put("name", "abc");
        map.put("age", 1);
        map.put("id",id);
        return map;
    }

}
