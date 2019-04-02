package com.twx.guanli.model;

import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tbs_guanli")
@Scope(value = WebApplicationContext.SCOPE_APPLICATION)
public class Guanli {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gid;
    @NotNull
    @Size(min = 1)
    private String gname;
    @NotNull
    @Size(min = 6)
    private String gpass;

    public Guanli(Long gid, String gname, String gpass) {
        this.gid = gid;
        this.gname = gname;
        this.gpass = gpass;
    }

    public Guanli() {
    }

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGpass() {
        return gpass;
    }

    public void setGpass(String gpass) {
        this.gpass = gpass;
    }
}
