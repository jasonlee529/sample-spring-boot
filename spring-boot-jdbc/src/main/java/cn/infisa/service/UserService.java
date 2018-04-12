package cn.infisa.service;

import cn.infisa.entity.User;
import cn.infisa.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User save(User user){
        userDao.save(user);
        return user;
    }

    public Optional<User> findOne(Long id){
        return userDao.findById(id);
    }

    public List<User> findAll(){
        return (List<User>) userDao.findAll();
    }

    public User update(Long id, User user) {
        user.setId(id);
        userDao.save(user);
        return user;
    }
}
