package com.whu.ticket.service.impl;

import com.whu.ticket.dao.EventMapper;
import com.whu.ticket.dao.EventRedisDao;
import com.whu.ticket.dao.OrderMapper;
import com.whu.ticket.dao.OrderRedisDao;
import com.whu.ticket.entity.Order;
import com.whu.ticket.service.DBAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DBAccessServiceImpl implements DBAccessService {

    @Autowired
    OrderRedisDao orderRedisDao;

    @Autowired
    EventRedisDao eventRedisDao;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    EventMapper eventMapper;

    @Async
    @Override
    public void consumeMessage() {
        Order order;
        do {
            order = orderRedisDao.getMessage();
            if (order == null) break;
            String orderToken = "event_" + order.getEvent_id() + "+" + "user_" + order.getUser_id();
            int rest = eventRedisDao.consumeQuota(order.getEvent_id(), order.getQuantity());
            if (rest >= 0) {
                orderRedisDao.updateOrderStatus(orderToken, 1);
                orderMapper.insertOrder(order);
                eventMapper.updateQuotaById(order.getEvent_id(), rest);
            } else {
                orderRedisDao.updateOrderStatus(orderToken, 0);
            }
        } while (true);
    }

    @Async
    @Override
    public void deleteOrder(Order order) {
        int rest = eventRedisDao.getQuota(order.getEvent_id());
        eventRedisDao.incQuota(order.getEvent_id(), order.getQuantity());
        orderMapper.deleteByIdAndUserId(order.getId(), order.getUser_id());
        eventMapper.updateQuotaById(order.getEvent_id(), rest + order.getQuantity());
    }
}
