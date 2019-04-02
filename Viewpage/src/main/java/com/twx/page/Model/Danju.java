package com.twx.page.Model;

import java.util.Date;

public class Danju {
    private Long id;  //订单编号 主键
    private Long uid; //用户id
    private Long bid; //书本id
    private Date jietime; //借出时间
    private Date huantime;//归还时间

    public Danju(Long id, Long uid, Long bid, Date jietime, Date huantime) {
        this.id = id;
        this.uid = uid;
        this.bid = bid;
        this.jietime = jietime;
        this.huantime = huantime;
    }

    public Danju() {
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

    public Date getJietime() {
        return jietime;
    }

    public void setJietime(Date jietime) {
        this.jietime = jietime;
    }

    public Date getHuantime() {
        return huantime;
    }

    public void setHuantime(Date huantime) {
        this.huantime = huantime;
    }
}
