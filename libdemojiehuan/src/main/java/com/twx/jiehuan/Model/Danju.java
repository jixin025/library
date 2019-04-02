package com.twx.jiehuan.Model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "tbs_danju")
public class Danju {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  //订单编号 主键
    private Long uid; //用户id
    private Long bid; //书本id
    private Date jietime; //借出时间
    private Date huantime;//归还时间

    public Danju() {
    }

    public Danju(Long id, Long uid, Long bid, Date jietime, Date huantime) {
        this.id = id;
        this.uid = uid;
        this.bid = bid;
        this.jietime = jietime;
        this.huantime = huantime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getJietime() {
        return jietime;
    }

    public void setJietime(Date jietime) {
        this.jietime = jietime;
    }
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getHuantime() {
        return huantime;
    }

    public void setHuantime(Date huantime) {
        this.huantime = huantime;
    }
}
