package com.dempe.forest.manger.utils;

/**
 * Created by Administrator on 2016/5/15 0015.
 */
public enum JSPForward {
    ROLE("admin/role"),
    USER("admin/user");
    private String path;

    private JSPForward(String path) {
        this.path = path;
    }

    public String path() {
        return path;
    }
}
