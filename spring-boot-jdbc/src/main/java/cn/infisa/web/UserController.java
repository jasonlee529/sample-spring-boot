package cn.infisa.web;

import cn.infisa.entity.User;
import cn.infisa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public User save(@RequestBody User user) {
        return userService.save(user);
    }
    @RequestMapping(value="{id}",method = RequestMethod.PUT)
    public User update(@PathVariable Long id,@RequestBody User user) {
        return userService.update(id,user);
    }
    @RequestMapping(method = RequestMethod.GET)
    public List<User> save() {
        return userService.findAll();
    }

}
