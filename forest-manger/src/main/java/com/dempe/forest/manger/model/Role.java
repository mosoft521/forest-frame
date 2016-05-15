package com.dempe.forest.manger.model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by Administrator on 2016/5/15 0015.
 */
@Entity("role")
public class Role {
    // 主键id
    @Id
    private String id;

    // 角色名称
    private String roleName;

    // 角色描述
    private String profile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
