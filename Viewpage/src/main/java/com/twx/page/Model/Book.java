package com.twx.page.Model;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Book {
    private Long bid;
    private String bname;
    private String bpress;
    private String bworks;
    private Date btime;
    private String bedsc;
    private String imgurl;
    private int bnum;


    public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBpress() {
        return bpress;
    }

    public void setBpress(String bpress) {
        this.bpress = bpress;
    }

    public String getBworks() {
        return bworks;
    }

    public void setBworks(String bworks) {
        this.bworks = bworks;
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getBtime() {
        return btime;
    }

    public void setBtime(Date btime) {
        this.btime = btime;
    }

    public String getBedsc() {
        return bedsc;
    }

    public void setBedsc(String bedsc) {
        this.bedsc = bedsc;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public int getBnum() {
        return bnum;
    }

    public void setBnum(int bnum) {
        this.bnum = bnum;
    }

    public Book(Long bid, String bname, String bpress, String bworks, Date btime, String bedsc, String imgurl, int bnum) {
        this.bid = bid;
        this.bname = bname;
        this.bpress = bpress;
        this.bworks = bworks;
        this.btime = btime;
        this.bedsc = bedsc;
        this.imgurl = imgurl;
        this.bnum = bnum;
    }

    public Book() {
    }
}
