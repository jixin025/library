package com.twx.jiehuan.Controller;

import com.twx.jiehuan.Model.Danju;
import com.twx.jiehuan.repository.DanjuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class DanjuController {

    Logger log = Logger.getLogger(this.getClass().getName());
    @Autowired
    DanjuRepository danjuRepository;

    //保存单据
    @RequestMapping("/jiebook")
    public String Jiebook(@RequestBody Danju danju) {
        log.info("===jiebook===" + danju.getBid() + "--uid--" + danju.getUid());
        Danju querydanju = danjuRepository.findByUidAndBid(danju.getUid(), danju.getBid());
        if (null==querydanju) {
            danjuRepository.save(danju);
            return "success";
        } else {
            return "fail";
        }
    }

    //查询用户所有借的书籍
    @RequestMapping("/jiebooklist")
    public List Jiebooklist(Long uid) {
        List<Danju> list = danjuRepository.findAllByUid(uid);
        return list;
    }

    //查询书本持有者
    @RequestMapping("/jieusrlist")
    public List Jieuserlist(Long bid) {
        List<Danju> userlist = danjuRepository.findAllByBid(bid);
        return userlist;
    }

    //归还图书
    @RequestMapping("/guihuan")
    public String Guihuan(Long uid, Long bid) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        int nowtime = Integer.parseInt(df.format(new Date()));
        Danju danju = danjuRepository.findByUidAndBid(uid, bid);
        int huantime = Integer.parseInt(df.format(danju.getHuantime()));
        if (huantime >= nowtime) {
            danjuRepository.deleteById(danju.getId());
            return "success";
        } else {
            return "fail";
        }
    }

    //续借图书
    @RequestMapping("/xujie")
    public String Xujie(Long uid, Long bid) {
        Danju olddanju = danjuRepository.findByUidAndBid(uid, bid);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date nowtime = new Date();
        int nowtimeint = Integer.parseInt(df.format(new Date()));
        int huantime = Integer.parseInt(df.format(olddanju.getHuantime()));
        if (huantime >= nowtimeint) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(nowtime);
            cal.add(Calendar.MONTH, 1);
            olddanju.setJietime(nowtime);
            olddanju.setHuantime(cal.getTime());
            danjuRepository.save(olddanju);
            return "success";
        } else {
            return "fail";
        }
    }

    //管理员归还
    @RequestMapping("/guanlihuan")
    public String Guanlihuan(Long uid, Long bid) {
        Danju danju = danjuRepository.findByUidAndBid(uid, bid);
        danjuRepository.deleteById(danju.getId());
        return "success";
    }

    //管理员续借
    @RequestMapping("/guanlixu")
    public String Guanlixu(Long uid, Long bid) {
        Danju olddanju = danjuRepository.findByUidAndBid(uid, bid);
        Date nowtime = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowtime);
        cal.add(Calendar.MONTH, 1);
        olddanju.setJietime(nowtime);
        olddanju.setHuantime(cal.getTime());
        danjuRepository.save(olddanju);
        return "success";
    }
}
