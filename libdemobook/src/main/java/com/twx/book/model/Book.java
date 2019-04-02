package com.twx.book.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "tbs_book")
@Scope(value = WebApplicationContext.SCOPE_APPLICATION)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //主键 书本id
    private Long bid;
    //书名 非空
    @NotNull
    private String bname;
    //书作者 非空
    @NotNull
    private String bworks;
    //书出版社 非空
    @NotNull
    private String bpress;
    //出版时间 非空
    @NotNull
    private Date btime;
    //书简介 非空
    @NotNull
    private String bedsc;
    //书图片的Url
    private String imgurl;
    //书数量 非空
    @NotNull
    private int bnum;

    public Book(Long bid, String bname, String bworks, String bpress, Date btime, String bedsc, String imgurl,  int bnum) {
        this.bid = bid;
        this.bname = bname;
        this.bworks = bworks;
        this.bpress = bpress;
        this.btime = btime;
        this.bedsc = bedsc;
        this.imgurl = imgurl;
        this.bnum=bnum;
    }

    public Book() {
    }

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

    public String getBworks() {
        return bworks;
    }

    public void setBworks(String bworks) {
        this.bworks = bworks;
    }

    public String getBpress() {
        return bpress;
    }

    public void setBpress(String bpress) {
        this.bpress = bpress;
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
}
