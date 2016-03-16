package com.dempe.forest.manger.model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/3/16
 * Time: 13:59
 * To change this template use File | Settings | File Templates.
 */
@Entity("user")
public class User {


    @Id
    private String uid;

    private String name;

    private String pwd;

    private String profile;

    private long createAt;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }
}
