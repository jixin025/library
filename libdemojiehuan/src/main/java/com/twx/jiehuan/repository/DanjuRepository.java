package com.twx.jiehuan.repository;

import com.twx.jiehuan.Model.Danju;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DanjuRepository extends JpaRepository<Danju,Long> {

    //通过uid查询所有订单
    List<Danju> findAllByUid(Long uid);
    //通过bid查询所有订单
    List<Danju> findAllByBid(Long bid);
    //通过uid和bid查询订单
    Danju findByUidAndBid(Long uid, Long bid);
}
