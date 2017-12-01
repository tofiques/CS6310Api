package com.dublabs.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tofiques on 3/10/17.
 */


public class Blackboard {


    private Integer id;

    private  String url;

    private  String userName;

    private String blackboardKey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBlackboardKey() {
        return blackboardKey;
    }

    public void setBlackboardKey(String blackboardKey) {
        this.blackboardKey = blackboardKey;
    }
}
