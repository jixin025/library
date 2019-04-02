package com.twx.user.model;

import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "tbs_user")
@Scope(value = WebApplicationContext.SCOPE_APPLICATION)
public class User {
    //用户ID 主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    //用户名 非空
    @NotNull
    @Size(min = 1)
    private String uname;
    //用户密码 非空
    @NotNull
    @Size(min = 6)
    private String upass;


    public User(Long uid, @NotNull @Size(min = 1) String uname, @NotNull @Size(min = 6) String upass ) {
        this.uid = uid;
        this.uname = uname;
        this.upass = upass;

    }

    public User() {
    }


    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }


}
