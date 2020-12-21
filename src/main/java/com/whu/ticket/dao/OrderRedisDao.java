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

    public void updateOrderStatus(String orderToken, int status) {
        // status = 1:finished or 0:rejected
        redisUtil.set(orderToken, status, 5);
    }

    public boolean popOrderStatus(String orderToken) {
        if (redisUtil.hasKey(orderToken)) {
            Integer status = (Integer) redisUtil.get(orderToken);
            redisUtil.del(orderToken);
            if (status == 1) {
                return true;
            } else {
                throw new RuntimeException("名额不足");
            }
        }
        return false;
    }
}
