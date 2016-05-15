package com.dempe.forest.manger.dao;

import com.dempe.forest.manger.model.Role;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/15 0015.
 */
@Repository
public class RoleDao extends BasicDAO<Role, Serializable> {

    @Autowired
    protected RoleDao(Datastore dataStore) {
        super(dataStore);
        ensureIndexes();// 自动创建索引
    }

}
