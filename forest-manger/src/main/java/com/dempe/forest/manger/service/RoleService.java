package com.dempe.forest.manger.service;

import com.dempe.forest.manger.dao.RoleDao;
import com.dempe.forest.manger.model.Role;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/5/15 0015.
 */
@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;


    public List<Role> list() {
        return roleDao.createQuery().asList();
    }

    public Role getById(String id) {
        return roleDao.get(id);
    }

    public void save(Role role){
        roleDao.save(role);
    }

    public void delById(String id) {
        roleDao.deleteById(id);
    }

}
