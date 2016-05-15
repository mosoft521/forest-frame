package com.dempe.forest.manger.service;

import com.dempe.forest.manger.dao.UserDao;
import com.dempe.forest.manger.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/3/16
 * Time: 14:01
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserService {


    @Autowired
    private UserDao userDao;


    public List<User> list() {
        return userDao.createQuery().asList();
    }

    public User getById(String id) {
        return userDao.get(id);
    }

    public void save(User user) {
        userDao.save(user);
    }

    public void delById(String id) {
        userDao.deleteById(id);
    }

    public User findByName(String name) {
        return userDao.findOne("name", name);
    }


}
