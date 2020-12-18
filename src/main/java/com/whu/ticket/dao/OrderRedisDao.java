package com.whu.ticket.dao;

import com.whu.ticket.entity.Order;
import com.whu.ticket.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRedisDao {
    @Autowired
    RedisUtil redisUtil;

    public void sendMessage(Order order) {
        redisUtil.lSet("orders", order);
    }

    public Order getMessage() {
        return (Order) redisUtil.lPop("orders", 30);
    }
}
