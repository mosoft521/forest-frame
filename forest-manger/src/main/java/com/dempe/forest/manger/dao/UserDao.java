package com.dempe.forest.manger.dao;

import com.dempe.forest.manger.model.User;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/3/16
 * Time: 14:00
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserDao extends BasicDAO<User, Serializable> {

    @Autowired
    protected UserDao(Datastore dataStore) {
        super(dataStore);
        ensureIndexes();// 自动创建索引
    }

    public Key<User> saveUser(User user) {
        user.setCreateAt(System.currentTimeMillis());
        return save(user);
    }

    public void updateUser(User user) {
        UpdateResults updateResults = updateFirst(createQuery().field("uid").equal(user.getUid()),
                createUpdateOperations().add("name", user.getName())
                        .add("profile", user.getProfile())
                        .add("pwd", user.getPwd()));

    }
}