package com.whu.ticket.service.impl;

import com.whu.ticket.dao.EventMapper;
import com.whu.ticket.dao.EventRedisDao;
import com.whu.ticket.dao.OrderMapper;
import com.whu.ticket.dao.OrderRedisDao;
import com.whu.ticket.entity.Order;
import com.whu.ticket.service.DBAccessService;
import com.whu.ticket.service.OrderService;
import com.whu.ticket.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderRedisDao orderRedisDao;

    @Autowired
    EventRedisDao eventRedisDao;

    @Autowired
    EventMapper eventMapper;

    @Autowired
    DBAccessService dbAccessService;

    @Override
    public void addOrder(Order order) {
        Integer quota = eventRedisDao.getQuota(order.getEvent_id());
        if (quota != null && quota < order.getQuantity()) {
            throw new RuntimeException("名额不足");
        }
//        eventRedisDao.incQuota(order.getEvent_id(), -order.getQuantity());
//        orderMapper.insertOrder(order);
        orderRedisDao.sendMessage(order);
        dbAccessService.consumeMessage();
    }

    @Override
    public void removeOrder(int id, int userId) {
        Order order = orderMapper.selectById(id);
        if (order == null) {
            throw new RuntimeException("订单不存在");
        }
        dbAccessService.deleteOrder(order);
    }

    @Override
    public int queryPageNums(int userId, int pageSize) {
        int tot = orderMapper.countByUserId(userId);
        if (tot % pageSize != 0) return tot / pageSize + 1;
        return tot / pageSize;
    }

    @Override
    public List<OrderVO> queryOrder(int userId, int pageNo, int pageSize) {
        if ((pageNo - 1) * pageSize >= orderMapper.countByUserId(userId)) {
            throw new RuntimeException("页码超出范围");
        }
        return orderMapper.selectByUserId(userId, (pageNo - 1) * pageSize, pageSize);
    }

    @Override
    public Order queryOrderInfo(int id, int userId) {
        return orderMapper.selectByIdAndUserId(id, userId);
    }

    @Override
    public boolean queryOrderStatus(int userId, int eventId) {
        String orderToken = "event_" + eventId + "+" + "user_" + userId;
        return orderRedisDao.popOrderStatus(orderToken);
    }
}
