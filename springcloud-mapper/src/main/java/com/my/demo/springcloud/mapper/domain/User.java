package com.my.demo.springcloud.mapper.domain;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_user")
public class User {

    @Id
    private String id;

    private String userName;

    private String userPassword;

    private String userage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserage() {
        return userage;
    }

    public void setUserage(String userage) {
        this.userage = userage == null ? null : userage.trim();
    }
}